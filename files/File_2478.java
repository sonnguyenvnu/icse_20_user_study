package com.hankcs.hanlp.classification.features;

/**
 * 仅仅使用TF的�?��?计算方�?
 */
public class TfOnlyFeatureWeighter implements IFeatureWeighter
{
    public double weight(int feature, int tf)
    {
        return tf;
    }
}
