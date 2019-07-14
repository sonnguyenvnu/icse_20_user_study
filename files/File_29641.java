package com.sohu.cache.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sohu.cache.web.controller.AppController;

/**
 * ç®€å?•æ–‡ä»¶è¯»å?–
 * @author leifu
 * @Date 2015å¹´3æœˆ2æ—¥
 * @Time ä¸‹å?ˆ2:12:15
 */
public class SimpleFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(SimpleFileUtil.class);

    /**
     * ä»ŽclassçŽ¯å¢ƒè¯»å?–æ–‡ä»¶æˆ?List<String>
     * @param fileName
     * @return
     */
    public static List<String> getListFromFile(String fileName, String encoding) {
        List<String> list = new ArrayList<String>();

        InputStream is = null;
        BufferedReader br = null;
        try {
            is = AppController.class.getClassLoader().getResourceAsStream(fileName);
            br = new BufferedReader(new InputStreamReader(is, encoding));
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return list;
    }
}
