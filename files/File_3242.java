package com.hankcs.hanlp.mining.word2vec;


import com.hankcs.hanlp.utility.TextUtility;

import java.io.IOException;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * �?�?��?训练工具
 */
public class Word2VecTrainer
{
    private Integer layerSize = 200;
    private Integer windowSize = 5;
    private Integer numThreads = Runtime.getRuntime().availableProcessors();
    private int negativeSamples = 25;
    private boolean useHierarchicalSoftmax;
    private Integer minFrequency = 5;
    private Float initialLearningRate;
    private float downSampleRate = 0.0001f;
    private Integer iterations = 15;
    private NeuralNetworkType type = NeuralNetworkType.CBOW;
    private TrainingCallback callback;

    public Word2VecTrainer()
    {
    }

    /**
     * 设置训练回调
     *
     * @param callback 回调接�?�
     */
    public void setCallback(TrainingCallback callback)
    {
        this.callback = callback;
    }

    /**
     * �?�?��?的维度（等�?�于神�?网络模型�?�?层的大�?）
     * <p>
     * 默认 100
     */
    public Word2VecTrainer setLayerSize(int layerSize)
    {
        Preconditions.checkArgument(layerSize > 0, "Value must be positive");
        this.layerSize = layerSize;
        return this;
    }

    /**
     * 窗�?�大�?
     * <p>
     * 默认 5
     */
    public Word2VecTrainer setWindowSize(int windowSize)
    {
        Preconditions.checkArgument(windowSize > 0, "Value must be positive");
        this.windowSize = windowSize;
        return this;
    }

    /**
     * 并行化训练线程数
     * <p>
     * 默认 {@link Runtime#availableProcessors()}
     */
    public Word2VecTrainer useNumThreads(int numThreads)
    {
        Preconditions.checkArgument(numThreads > 0, "Value must be positive");
        this.numThreads = numThreads;
        return this;
    }

    /**
     * 神�?网络类型
     *
     * @see {@link NeuralNetworkType}
     * <p>
     * 默认 {@link NeuralNetworkType#SKIP_GRAM}
     */
    public Word2VecTrainer type(NeuralNetworkType type)
    {
        this.type = Preconditions.checkNotNull(type);
        return this;
    }

    /**
     * �?�用 hierarchical softmax
     * <p>
     * 默认关闭
     */
    public Word2VecTrainer useHierarchicalSoftmax()
    {
        this.useHierarchicalSoftmax = true;
        return this;
    }

    /**
     * 负采样样本数
     * 一般在 5 到 10 之间
     * <p>
     * 默认 0
     */
    public Word2VecTrainer useNegativeSamples(int negativeSamples)
    {
        Preconditions.checkArgument(negativeSamples >= 0, "Value must be non-negative");
        this.negativeSamples = negativeSamples;
        return this;
    }

    /**
     * 最低�?频，低于此数值将被过滤掉
     * <p>
     * 默认 5
     */
    public Word2VecTrainer setMinVocabFrequency(int minFrequency)
    {
        Preconditions.checkArgument(minFrequency >= 0, "Value must be non-negative");
        this.minFrequency = minFrequency;
        return this;
    }

    /**
     * 设置�?始学习率
     * <p>
     * skip-gram 默认 0.025 ，CBOW 默认 0.05
     */
    public Word2VecTrainer setInitialLearningRate(float initialLearningRate)
    {
        Preconditions.checkArgument(initialLearningRate >= 0, "Value must be non-negative");
        this.initialLearningRate = initialLearningRate;
        return this;
    }

    /**
     * 设置高频�?的下采样频率（高频�?频率一旦高于此频率，训练时将被�?机忽略），在�?使用�?�用�?�?典的情况下，�?�用�?就符�?�高频�?的标准
     * <p>
     * 默认 1e-3, 常用�?�值区间为 (0, 1e-5)
     */
    public Word2VecTrainer setDownSamplingRate(float downSampleRate)
    {
        Preconditions.checkArgument(downSampleRate >= 0, "Value must be non-negative");
        this.downSampleRate = downSampleRate;
        return this;
    }

    /**
     * 设置迭代次数
     */
    public Word2VecTrainer setNumIterations(int iterations)
    {
        Preconditions.checkArgument(iterations > 0, "Value must be positive");
        this.iterations = iterations;
        return this;
    }


    /**
     * 执行训练
     *
     * @param trainFileName     输入语料文件
     * @param modelFileName     输出模型路径
     * @return �?�?��?模型
     */
    public WordVectorModel train(String trainFileName, String modelFileName)
    {
        Config settings = new Config();
        settings.setInputFile(trainFileName);
        settings.setLayer1Size(layerSize);
        settings.setUseContinuousBagOfWords(type == NeuralNetworkType.CBOW);
        settings.setUseHierarchicalSoftmax(useHierarchicalSoftmax);
        settings.setNegative(negativeSamples);
        settings.setNumThreads(numThreads);
        settings.setAlpha(initialLearningRate == null ? type.getDefaultInitialLearningRate() : initialLearningRate);
        settings.setSample(downSampleRate);
        settings.setWindow(windowSize);
        settings.setIter(iterations);
        settings.setMinCount(minFrequency);
        settings.setOutputFile(modelFileName);
        Word2VecTraining model = new Word2VecTraining(settings);
        final long timeStart = System.currentTimeMillis();
//        if (callback == null)
//        {
//            callback = new TrainingCallback()
//            {
//                public void corpusLoading(float percent)
//                {
//                    System.out.printf("\r加载训练语料：%.2f%%", percent);
//                }
//
//                public void corpusLoaded(int vocWords, int trainWords, int totalWords)
//                {
//                    System.out.println();
//                    System.out.printf("�?表大�?：%d\n", vocWords);
//                    System.out.printf("训练�?数：%d\n", trainWords);
//                    System.out.printf("语料�?数：%d\n", totalWords);
//                }
//
//                public void training(float alpha, float progress)
//                {
//                    System.out.printf("\r学习率：%.6f  进度：%.2f%%", alpha, progress);
//                    long timeNow = System.currentTimeMillis();
//                    long costTime = timeNow - timeStart + 1;
//                    progress /= 100;
//                    String etd = Utility.humanTime((long) (costTime / progress * (1.f - progress)));
//                    if (etd.length() > 0) System.out.printf("  剩余时间：%s", etd);
//                    System.out.flush();
//                }
//            };
//        }
        settings.setCallback(callback);

        try
        {
            model.trainModel();
            System.out.println();
            System.out.printf("训练结�?�，一共耗时：%s\n", Utility.humanTime(System.currentTimeMillis() - timeStart));
            return new WordVectorModel(modelFileName);
        }
        catch (IOException e)
        {
            logger.warning("训练过程中�?�生IO异常\n" + TextUtility.exceptionToString(e));
        }

        return null;
    }
}
