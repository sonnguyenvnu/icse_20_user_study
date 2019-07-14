/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>me@hankcs.com</email>
 * <create-date>16/2/10 PM4:56</create-date>
 *
 * <copyright file="Catalog.java" company="�?农场">
 * Copyright (c) 2008-2016, �?农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.classification.corpus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 类目�??称和id的对应关系
 * @author hankcs
 */
public class Catalog implements Serializable
{
    Map<String, Integer> categoryId;
    List<String> idCategory;

    public Catalog()
    {
        categoryId = new TreeMap<String, Integer>();
        idCategory = new ArrayList<String>();
    }

    public Catalog(String[] catalog)
    {
        this();
        for (int i = 0; i < catalog.length; i++)
        {
            categoryId.put(catalog[i], i);
            idCategory.add(catalog[i]);
        }
    }

    public int addCategory(String category)
    {
        Integer id = categoryId.get(category);
        if (id == null)
        {
            id = categoryId.size();
            categoryId.put(category, id);
            assert idCategory.size() == id;
            idCategory.add(category);
        }

        return id;
    }

    public Integer getId(String category)
    {
        return categoryId.get(category);
    }

    public String getCategory(int id)
    {
        assert 0 <= id;
        assert id < idCategory.size();

        return idCategory.get(id);
    }

    public List<String> getCategories()
    {
        return idCategory;
    }

    public int size()
    {
        return idCategory.size();
    }

    public String[] toArray()
    {
        String[] catalog = new String[idCategory.size()];
        idCategory.toArray(catalog);

        return catalog;
    }

    @Override
    public String toString()
    {
        return idCategory.toString();
    }
}
