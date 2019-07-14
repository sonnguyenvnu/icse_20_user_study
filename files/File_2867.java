/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/11/20 17:24</create-date>
 *
 * <copyright file="WordNatureDependencyParser.java" company="ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸">
 * Copyright (c) 2003-2014, ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact ä¸Šæµ·æž—åŽŸä¿¡æ?¯ç§‘æŠ€æœ‰é™?å…¬å?¸ to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.dependency;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.dartsclone.Pair;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.io.ByteArray;
import com.hankcs.hanlp.corpus.io.ByteArrayFileStream;
import com.hankcs.hanlp.dependency.common.Edge;
import com.hankcs.hanlp.dependency.common.Node;
import com.hankcs.hanlp.dependency.perceptron.parser.KBeamArcEagerDependencyParser;
import com.hankcs.hanlp.model.maxent.MaxEntModel;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.utility.GlobalObjectPool;
import com.hankcs.hanlp.utility.Predefine;

import java.util.LinkedList;
import java.util.List;
import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * æœ€å¤§ç†µå?¥æ³•åˆ†æž?å™¨
 *
 * @deprecated å·²åºŸå¼ƒï¼Œè¯·ä½¿ç”¨{@link KBeamArcEagerDependencyParser}ã€‚æœªæ?¥ç‰ˆæœ¬å°†ä¸?å†?å?‘å¸ƒè¯¥æ¨¡åž‹ï¼Œå¹¶åˆ é™¤é…?ç½®é¡¹
 * @author hankcs
 */
public class MaxEntDependencyParser extends MinimumSpanningTreeParser
{
    private MaxEntModel model;

    public MaxEntDependencyParser(MaxEntModel model)
    {
        this.model = model;
    }

    public MaxEntDependencyParser()
    {
        String path = HanLP.Config.MaxEntModelPath + Predefine.BIN_EXT;
        model = GlobalObjectPool.get(path);
        if (model != null) return;
        long start = System.currentTimeMillis();
        ByteArray byteArray = ByteArrayFileStream.createByteArrayFileStream(path);
        if (byteArray != null)
        {
            model = MaxEntModel.create(byteArray);
        }
        else
        {
            model = MaxEntModel.create(HanLP.Config.MaxEntModelPath);
        }
        if (model != null)
        {
            GlobalObjectPool.put(path, model);
        }
        String result = model == null ? "å¤±è´¥" : "æˆ?åŠŸ";
        logger.info("æœ€å¤§ç†µä¾?å­˜å?¥æ³•æ¨¡åž‹è½½å…¥" + result + "ï¼Œè€—æ—¶" + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * åˆ†æž?å?¥å­?çš„ä¾?å­˜å?¥æ³•
     *
     * @param termList å?¥å­?ï¼Œå?¯ä»¥æ˜¯ä»»ä½•å…·æœ‰è¯?æ€§æ ‡æ³¨åŠŸèƒ½çš„åˆ†è¯?å™¨çš„åˆ†è¯?ç»“æžœ
     * @return CoNLLæ ¼å¼?çš„ä¾?å­˜å?¥æ³•æ ‘
     */
    public static CoNLLSentence compute(List<Term> termList)
    {
        return new MaxEntDependencyParser().parse(termList);
    }

    /**
     * åˆ†æž?å?¥å­?çš„ä¾?å­˜å?¥æ³•
     *
     * @param sentence å?¥å­?
     * @return CoNLLæ ¼å¼?çš„ä¾?å­˜å?¥æ³•æ ‘
     */
    public static CoNLLSentence compute(String sentence)
    {
        return new MaxEntDependencyParser().parse(sentence);
    }

    @Override
    protected Edge makeEdge(Node[] nodeArray, int from, int to)
    {
        LinkedList<String> context = new LinkedList<String>();
        int index = from;
        for (int i = index - 2; i < index + 2 + 1; ++i)
        {
            Node w = i >= 0 && i < nodeArray.length ? nodeArray[i] : Node.NULL;
            context.add(w.compiledWord + "i" + (i - index));      // åœ¨å°¾å·´ä¸Šå?šä¸ªæ ‡è®°ï¼Œä¸?ç„¶ç‰¹å¾?å†²çª?äº†
            context.add(w.label + "i" + (i - index));
        }
        index = to;
        for (int i = index - 2; i < index + 2 + 1; ++i)
        {
            Node w = i >= 0 && i < nodeArray.length ? nodeArray[i] : Node.NULL;
            context.add(w.compiledWord + "j" + (i - index));      // åœ¨å°¾å·´ä¸Šå?šä¸ªæ ‡è®°ï¼Œä¸?ç„¶ç‰¹å¾?å†²çª?äº†
            context.add(w.label + "j" + (i - index));
        }
        context.add(nodeArray[from].compiledWord + 'â†’' + nodeArray[to].compiledWord);
        context.add(nodeArray[from].label + 'â†’' + nodeArray[to].label);
        context.add(nodeArray[from].compiledWord + 'â†’' + nodeArray[to].compiledWord + (from - to));
        context.add(nodeArray[from].label + 'â†’' + nodeArray[to].label + (from - to));
        Node wordBeforeI = from - 1 >= 0 ? nodeArray[from - 1] : Node.NULL;
        Node wordBeforeJ = to - 1 >= 0 ? nodeArray[to - 1] : Node.NULL;
        context.add(wordBeforeI.compiledWord + '@' + nodeArray[from].compiledWord + 'â†’' + nodeArray[to].compiledWord);
        context.add(nodeArray[from].compiledWord + 'â†’' + wordBeforeJ.compiledWord + '@' + nodeArray[to].compiledWord);
        context.add(wordBeforeI.label + '@' + nodeArray[from].label + 'â†’' + nodeArray[to].label);
        context.add(nodeArray[from].label + 'â†’' + wordBeforeJ.label + '@' + nodeArray[to].label);
        List<Pair<String, Double>> pairList = model.predict(context.toArray(new String[0]));
        Pair<String, Double> maxPair = new Pair<String, Double>("null", -1.0);
//        System.out.println(context);
//        System.out.println(pairList);
        for (Pair<String, Double> pair : pairList)
        {
            if (pair.getValue() > maxPair.getValue() && !"null".equals(pair.getKey()))
            {
                maxPair = pair;
            }
        }
//        System.out.println(nodeArray[from].word + "â†’" + nodeArray[to].word + " : " + maxPair);

        return new Edge(from, to, maxPair.getKey(), (float) - Math.log(maxPair.getValue()));
    }
}
