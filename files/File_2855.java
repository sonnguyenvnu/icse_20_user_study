/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/5/10 14:34</create-date>
 *
 * <copyright file="Nature.java" company="上海林原信�?�科技有�?公�?�">
 * Copyright (c) 2003-2014, 上海林原信�?�科技有�?公�?�. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信�?�科技有�?公�?� to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.corpus.tag;

import java.util.TreeMap;

/**
 * �?性
 *
 * @author hankcs
 */
public class Nature
{
    /**
     * 区别语素
     */
    public static final Nature bg = new Nature("bg");

    /**
     * 数语素
     */
    public static final Nature mg = new Nature("mg");

    /**
     * �??�?性惯用语
     */
    public static final Nature nl = new Nature("nl");

    /**
     * 字�?专�??
     */
    public static final Nature nx = new Nature("nx");

    /**
     * �?�?语素
     */
    public static final Nature qg = new Nature("qg");

    /**
     * 助�?
     */
    public static final Nature ud = new Nature("ud");

    /**
     * 助�?
     */
    public static final Nature uj = new Nature("uj");

    /**
     * �?�
     */
    public static final Nature uz = new Nature("uz");

    /**
     * 过
     */
    public static final Nature ug = new Nature("ug");

    /**
     * 连�?
     */
    public static final Nature ul = new Nature("ul");

    /**
     * 连�?
     */
    public static final Nature uv = new Nature("uv");

    /**
     * 语气语素
     */
    public static final Nature yg = new Nature("yg");

    /**
     * 状�?�?
     */
    public static final Nature zg = new Nature("zg");

    // 以上标签�?�自ICT，以下标签�?�自北大

    /**
     * �??�?
     */
    public static final Nature n = new Nature("n");

    /**
     * 人�??
     */
    public static final Nature nr = new Nature("nr");

    /**
     * 日语人�??
     */
    public static final Nature nrj = new Nature("nrj");

    /**
     * 音译人�??
     */
    public static final Nature nrf = new Nature("nrf");

    /**
     * �?姓
     */
    public static final Nature nr1 = new Nature("nr1");

    /**
     * 蒙�?�姓�??
     */
    public static final Nature nr2 = new Nature("nr2");

    /**
     * 地�??
     */
    public static final Nature ns = new Nature("ns");

    /**
     * 音译地�??
     */
    public static final Nature nsf = new Nature("nsf");

    /**
     * 机构团体�??
     */
    public static final Nature nt = new Nature("nt");

    /**
     * 公�?��??
     */
    public static final Nature ntc = new Nature("ntc");

    /**
     * 工厂
     */
    public static final Nature ntcf = new Nature("ntcf");

    /**
     * 银行
     */
    public static final Nature ntcb = new Nature("ntcb");

    /**
     * 酒店宾馆
     */
    public static final Nature ntch = new Nature("ntch");

    /**
     * 政府机构
     */
    public static final Nature nto = new Nature("nto");

    /**
     * 大学
     */
    public static final Nature ntu = new Nature("ntu");

    /**
     * 中�?学
     */
    public static final Nature nts = new Nature("nts");

    /**
     * 医院
     */
    public static final Nature nth = new Nature("nth");

    /**
     * 医�?�疾病等�?�康相关�??�?
     */
    public static final Nature nh = new Nature("nh");

    /**
     * �?��?
     */
    public static final Nature nhm = new Nature("nhm");

    /**
     * 疾病
     */
    public static final Nature nhd = new Nature("nhd");

    /**
     * 工作相关�??�?
     */
    public static final Nature nn = new Nature("nn");

    /**
     * �?�务�?�称
     */
    public static final Nature nnt = new Nature("nnt");

    /**
     * �?�业
     */
    public static final Nature nnd = new Nature("nnd");

    /**
     * �??�?性语素
     */
    public static final Nature ng = new Nature("ng");

    /**
     * 食�?，比如“薯片�?
     */
    public static final Nature nf = new Nature("nf");

    /**
     * 机构相关（�?是独立机构�??）
     */
    public static final Nature ni = new Nature("ni");

    /**
     * 教育相关机构
     */
    public static final Nature nit = new Nature("nit");

    /**
     * 下属机构
     */
    public static final Nature nic = new Nature("nic");

    /**
     * 机构�?�缀
     */
    public static final Nature nis = new Nature("nis");

    /**
     * 物�?�??
     */
    public static final Nature nm = new Nature("nm");

    /**
     * 化学�?�??
     */
    public static final Nature nmc = new Nature("nmc");

    /**
     * 生物�??
     */
    public static final Nature nb = new Nature("nb");

    /**
     * 动物�??
     */
    public static final Nature nba = new Nature("nba");

    /**
     * 动物纲目
     */
    public static final Nature nbc = new Nature("nbc");

    /**
     * �?物�??
     */
    public static final Nature nbp = new Nature("nbp");

    /**
     * 其他专�??
     */
    public static final Nature nz = new Nature("nz");

    /**
     * 学术�?汇
     */
    public static final Nature g = new Nature("g");

    /**
     * 数学相关�?汇
     */
    public static final Nature gm = new Nature("gm");

    /**
     * 物�?�相关�?汇
     */
    public static final Nature gp = new Nature("gp");

    /**
     * 化学相关�?汇
     */
    public static final Nature gc = new Nature("gc");

    /**
     * 生物相关�?汇
     */
    public static final Nature gb = new Nature("gb");

    /**
     * 生物类别
     */
    public static final Nature gbc = new Nature("gbc");

    /**
     * 地�?�地质相关�?汇
     */
    public static final Nature gg = new Nature("gg");

    /**
     * 计算机相关�?汇
     */
    public static final Nature gi = new Nature("gi");

    /**
     * 简称略语
     */
    public static final Nature j = new Nature("j");

    /**
     * �?语
     */
    public static final Nature i = new Nature("i");

    /**
     * 习用语
     */
    public static final Nature l = new Nature("l");

    /**
     * 时间�?
     */
    public static final Nature t = new Nature("t");

    /**
     * 时间�?性语素
     */
    public static final Nature tg = new Nature("tg");

    /**
     * 处所�?
     */
    public static final Nature s = new Nature("s");

    /**
     * 方�?�?
     */
    public static final Nature f = new Nature("f");

    /**
     * 动�?
     */
    public static final Nature v = new Nature("v");

    /**
     * 副动�?
     */
    public static final Nature vd = new Nature("vd");

    /**
     * �??动�?
     */
    public static final Nature vn = new Nature("vn");

    /**
     * 动�?“是�?
     */
    public static final Nature vshi = new Nature("vshi");

    /**
     * 动�?“有�?
     */
    public static final Nature vyou = new Nature("vyou");

    /**
     * 趋�?�动�?
     */
    public static final Nature vf = new Nature("vf");

    /**
     * 形�?动�?
     */
    public static final Nature vx = new Nature("vx");

    /**
     * �?�?�物动�?（内动�?）
     */
    public static final Nature vi = new Nature("vi");

    /**
     * 动�?性惯用语
     */
    public static final Nature vl = new Nature("vl");

    /**
     * 动�?性语素
     */
    public static final Nature vg = new Nature("vg");

    /**
     * 形容�?
     */
    public static final Nature a = new Nature("a");

    /**
     * 副形�?
     */
    public static final Nature ad = new Nature("ad");

    /**
     * �??形�?
     */
    public static final Nature an = new Nature("an");

    /**
     * 形容�?性语素
     */
    public static final Nature ag = new Nature("ag");

    /**
     * 形容�?性惯用语
     */
    public static final Nature al = new Nature("al");

    /**
     * 区别�?
     */
    public static final Nature b = new Nature("b");

    /**
     * 区别�?性惯用语
     */
    public static final Nature bl = new Nature("bl");

    /**
     * 状�?�?
     */
    public static final Nature z = new Nature("z");

    /**
     * 代�?
     */
    public static final Nature r = new Nature("r");

    /**
     * 人称代�?
     */
    public static final Nature rr = new Nature("rr");

    /**
     * 指示代�?
     */
    public static final Nature rz = new Nature("rz");

    /**
     * 时间指示代�?
     */
    public static final Nature rzt = new Nature("rzt");

    /**
     * 处所指示代�?
     */
    public static final Nature rzs = new Nature("rzs");

    /**
     * 谓�?性指示代�?
     */
    public static final Nature rzv = new Nature("rzv");

    /**
     * 疑问代�?
     */
    public static final Nature ry = new Nature("ry");

    /**
     * 时间疑问代�?
     */
    public static final Nature ryt = new Nature("ryt");

    /**
     * 处所疑问代�?
     */
    public static final Nature rys = new Nature("rys");

    /**
     * 谓�?性疑问代�?
     */
    public static final Nature ryv = new Nature("ryv");

    /**
     * 代�?性语素
     */
    public static final Nature rg = new Nature("rg");

    /**
     * �?�汉语代�?性语素
     */
    public static final Nature Rg = new Nature("Rg");

    /**
     * 数�?
     */
    public static final Nature m = new Nature("m");

    /**
     * 数�?�?
     */
    public static final Nature mq = new Nature("mq");

    /**
     * 甲乙丙�?之类的数�?
     */
    public static final Nature Mg = new Nature("Mg");

    /**
     * �?�?
     */
    public static final Nature q = new Nature("q");

    /**
     * 动�?�?
     */
    public static final Nature qv = new Nature("qv");

    /**
     * 时�?�?
     */
    public static final Nature qt = new Nature("qt");

    /**
     * 副�?
     */
    public static final Nature d = new Nature("d");

    /**
     * 辄,俱,�?之类的副�?
     */
    public static final Nature dg = new Nature("dg");

    /**
     * 连语
     */
    public static final Nature dl = new Nature("dl");

    /**
     * 介�?
     */
    public static final Nature p = new Nature("p");

    /**
     * 介�?“把�?
     */
    public static final Nature pba = new Nature("pba");

    /**
     * 介�?“被�?
     */
    public static final Nature pbei = new Nature("pbei");

    /**
     * 连�?
     */
    public static final Nature c = new Nature("c");

    /**
     * 并列连�?
     */
    public static final Nature cc = new Nature("cc");

    /**
     * 助�?
     */
    public static final Nature u = new Nature("u");

    /**
     * �?�
     */
    public static final Nature uzhe = new Nature("uzhe");

    /**
     * 了 喽
     */
    public static final Nature ule = new Nature("ule");

    /**
     * 过
     */
    public static final Nature uguo = new Nature("uguo");

    /**
     * 的 底
     */
    public static final Nature ude1 = new Nature("ude1");

    /**
     * 地
     */
    public static final Nature ude2 = new Nature("ude2");

    /**
     * 得
     */
    public static final Nature ude3 = new Nature("ude3");

    /**
     * 所
     */
    public static final Nature usuo = new Nature("usuo");

    /**
     * 等 等等 云云
     */
    public static final Nature udeng = new Nature("udeng");

    /**
     * 一样 一般 似的 般
     */
    public static final Nature uyy = new Nature("uyy");

    /**
     * 的�?
     */
    public static final Nature udh = new Nature("udh");

    /**
     * �?�讲 �?�说 而言 说�?�
     */
    public static final Nature uls = new Nature("uls");

    /**
     * 之
     */
    public static final Nature uzhi = new Nature("uzhi");

    /**
     * 连 （“连�?学生都会�?）
     */
    public static final Nature ulian = new Nature("ulian");

    /**
     * �?��?
     */
    public static final Nature e = new Nature("e");

    /**
     * 语气�?(delete yg)
     */
    public static final Nature y = new Nature("y");

    /**
     * 拟声�?
     */
    public static final Nature o = new Nature("o");

    /**
     * �?缀
     */
    public static final Nature h = new Nature("h");

    /**
     * �?�缀
     */
    public static final Nature k = new Nature("k");

    /**
     * 字符串
     */
    public static final Nature x = new Nature("x");

    /**
     * �?�语素字
     */
    public static final Nature xx = new Nature("xx");

    /**
     * 网�?�URL
     */
    public static final Nature xu = new Nature("xu");

    /**
     * 标点符�?�
     */
    public static final Nature w = new Nature("w");

    /**
     * 左括�?�，全角：（ 〔  ［  ｛  《 �?  〖 〈   �?�角：( [ { <
     */
    public static final Nature wkz = new Nature("wkz");

    /**
     * �?�括�?�，全角：） 〕  ］ �? 》  】 〗 〉 �?�角： ) ] { >
     */
    public static final Nature wky = new Nature("wky");

    /**
     * 左引�?�，全角：“ ‘ 『
     */
    public static final Nature wyz = new Nature("wyz");

    /**
     * �?�引�?�，全角：�? ’ �?
     */
    public static final Nature wyy = new Nature("wyy");

    /**
     * �?��?�，全角：。
     */
    public static final Nature wj = new Nature("wj");

    /**
     * 问�?�，全角：？ �?�角：?
     */
    public static final Nature ww = new Nature("ww");

    /**
     * �?��?�，全角：�? �?�角：!
     */
    public static final Nature wt = new Nature("wt");

    /**
     * 逗�?�，全角：， �?�角：,
     */
    public static final Nature wd = new Nature("wd");

    /**
     * 分�?�，全角：； �?�角： ;
     */
    public static final Nature wf = new Nature("wf");

    /**
     * 顿�?�，全角：�?
     */
    public static final Nature wn = new Nature("wn");

    /**
     * 冒�?�，全角：： �?�角： :
     */
    public static final Nature wm = new Nature("wm");

    /**
     * �?略�?�，全角：……  …
     */
    public static final Nature ws = new Nature("ws");

    /**
     * 破折�?�，全角：——   �?�?   ——�?   �?�角：---  ----
     */
    public static final Nature wp = new Nature("wp");

    /**
     * 百分�?��?�分�?�，全角：％ ‰   �?�角：%
     */
    public static final Nature wb = new Nature("wb");

    /**
     * �?��?符�?�，全角：￥ ＄ ￡  °  ℃  �?�角：$
     */
    public static final Nature wh = new Nature("wh");

    /**
     * 仅用于终##终，�?会出现在分�?结果中
     */
    public static final Nature end = new Nature("end");

    /**
     * 仅用于始##始，�?会出现在分�?结果中
     */
    public static final Nature begin = new Nature("begin");

    private static TreeMap<String, Integer> idMap;
    private static Nature[] values;
    private int ordinal;
    private final String name;

    private Nature(String name)
    {
        if (idMap == null) idMap = new TreeMap<String, Integer>();
        assert !idMap.containsKey(name);
        this.name = name;
        ordinal = idMap.size();
        idMap.put(name, ordinal);
        Nature[] extended = new Nature[idMap.size()];
        if (values != null)
            System.arraycopy(values, 0, extended, 0, values.length);
        extended[ordinal] = this;
        values = extended;
    }

    /**
     * �?性是�?�以该�?缀开头<br>
     * �?性根�?�开头的几个字�?�?�以判断大的类别
     *
     * @param prefix �?缀
     * @return 是�?�以该�?缀开头
     */
    public boolean startsWith(String prefix)
    {
        return name.startsWith(prefix);
    }

    /**
     * �?性是�?�以该�?缀开头<br>
     * �?性根�?�开头的几个字�?�?�以判断大的类别
     *
     * @param prefix �?缀
     * @return 是�?�以该�?缀开头
     */
    public boolean startsWith(char prefix)
    {
        return name.charAt(0) == prefix;
    }

    /**
     * �?性的首字�?<br>
     * �?性根�?�开头的几个字�?�?�以判断大的类别
     *
     * @return
     */
    public char firstChar()
    {
        return name.charAt(0);
    }

    /**
     * 安全地将字符串类型的�?性转为Enum类型，如果未定义该�?性，则返回null
     *
     * @param name 字符串�?性
     * @return Enum�?性
     */
    public static final Nature fromString(String name)
    {
        Integer id = idMap.get(name);
        if (id == null)
            return null;
        return values[id];
    }

    /**
     * 创建自定义�?性,如果已有该对应�?性,则直接返回已有的�?性
     *
     * @param name 字符串�?性
     * @return Enum�?性
     */
    public static final Nature create(String name)
    {
        Nature nature = fromString(name);
        if (nature == null)
            return new Nature(name);
        return nature;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public int ordinal()
    {
        return ordinal;
    }

    public static Nature[] values()
    {
        return values;
    }
}
