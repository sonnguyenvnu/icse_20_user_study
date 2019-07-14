/*
 * <author>Han He</author>
 * <email>me@hankcs.com</email>
 * <create-date>2018-06-21 9:04 AM</create-date>
 *
 * <copyright file="LockableFeatureMap.java">
 * Copyright (c) 2018, Han He. All Rights Reserved, http://www.hankcs.com/
 * This source is subject to Han He. Please contact Han He for more information.
 * </copyright>
 */
package com.hankcs.hanlp.model.perceptron.feature;

import com.hankcs.hanlp.model.perceptron.tagset.TagSet;

/**
 * å?¯åˆ‡æ?¢é”?å®šçŠ¶æ€?çš„ç‰¹å¾?idæ˜ å°„
 *
 * @author hankcs
 */
public class LockableFeatureMap extends ImmutableFeatureMDatMap
{
    public LockableFeatureMap(TagSet tagSet)
    {
        super(tagSet);
    }

    @Override
    public int idOf(String string)
    {
        int id = super.idOf(string); // æŸ¥è¯¢id
        if (id == -1 && mutable) // å¦‚æžœä¸?å­˜åœ¨è¯¥keyä¸”å¤„äºŽå?¯å†™çŠ¶æ€?
        {
            id = dat.size();
            dat.put(string, id); // åˆ™ä¸ºkeyåˆ†é…?æ–°id
        }
        return id;
    }
}
