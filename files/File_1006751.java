package org.javacore.io.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
 * Copyright [2015] [Jeff Lee]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Jeff Lee
 * @since 2015-10-17 14:58:59
 *  利用Zip进行多文件�?存
 */
public class ZipCompress {
    private static String filePath = "src" + File.separator +
            "org" + File.separator +
            "javacore" + File.separator +
            "io" + File.separator;

    private static String[]  fileNames= new String[] {
        filePath + "BufferedInputFileT.java",
        filePath + "ChangeSystemOut.java"
    };

    public static void main(String[] args) throws IOException {
        zipFiles(fileNames);
    }

    private static void zipFiles(String[] fileNames)
            throws IOException {
        // 获�?�zip文件输出�?
        FileOutputStream f = new FileOutputStream("test.zip");
        // 从文件输出�?中获�?�数�?�校验和输出�?,并设置Adler32
        CheckedOutputStream csum = new CheckedOutputStream(f,new Adler32());
        // 从数�?�校验和输出�?中获�?�Zip输出�?
        ZipOutputStream zos = new ZipOutputStream(csum);
        // 从Zip输出�?中获�?�缓冲输出�?
        BufferedOutputStream out = new BufferedOutputStream(zos);
        // 设置Zip文件注释
        zos.setComment("测试 java zip stream");
        for (String file : fileNames) {
            System.out.println("写入文件: " + file);
            // 获�?�文件输入字符�?
            BufferedReader in =
                    new BufferedReader(new FileReader(file));
            // 想Zip处�?�写入新的文件�?�目，并�?定�?到数�?�开始处
            zos.putNextEntry(new ZipEntry(file));
            int c;
            while ((c = in.read()) > 0)
                out.write(c);
            in.close();
            // 刷新Zip输出�?，将缓冲的�?写入该�?
            out.flush();
        }
        // 文件全部写入Zip输出�?�?�，关闭
        out.close();

        // 输出数�?�校验和
        System.out.println("数�?�校验和: " + csum.getChecksum().getValue());
        System.out.println("读�?�zip文件");
        // 读�?�test.zip文件输入�?
        FileInputStream fi = new FileInputStream("test.zip");
        // 从文件输入�?中获�?�数�?�校验和�?
        CheckedInputStream csumi = new CheckedInputStream(fi,new Adler32());
        // 从数�?�校验和�?中获�?�Zip解压�?
        ZipInputStream in2 = new ZipInputStream(csumi);
        // 从Zip解压�?中获�?�缓冲输入�?
        BufferedInputStream bis = new BufferedInputStream(in2);
        // 创建文件�?�目
        ZipEntry zipEntry;
        while ((zipEntry = in2.getNextEntry()) != null) {
            System.out.println("读�?�文件： " + zipEntry);
            int x;
            while ((x = bis.read()) > 0)
                System.out.write(x);
        }
        if (fileNames.length == 1)
            System.out.println("数�?�校验和： " + csumi.getChecksum().getValue());
        bis.close();

        // 获�?�Zip文件
        ZipFile zf = new ZipFile("test.zip");
        // 获�?�文件�?�目枚举
        Enumeration e = zf.entries();
        while (e.hasMoreElements()) {
            // 从Zip文件的枚举中获�?�文件�?�目
            ZipEntry ze2 = (ZipEntry) e.nextElement();
            System.out.println("文件： " + ze2);
        }

    }
}
