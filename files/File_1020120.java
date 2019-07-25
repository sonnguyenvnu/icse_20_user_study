package com.myimooc.java.thumbnail.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 缩略图�?务类
 *
 * @author ZhangCheng on 2017-07-19
 */
@Service
public class ThumbnailAwtService {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    @SuppressWarnings("static-access")
    public String thumbnail(MultipartFile file, String uploadPath, String realUploadPath) {

        OutputStream os = null;

        try {
            String des = realUploadPath + "/thum_" + file.getOriginalFilename();

            os = new FileOutputStream(des);

            Image image = ImageIO.read(file.getInputStream());
            // 原图狂宽度
            int width = image.getWidth(null);
            // 原图高度
            int height = image.getHeight(null);

            // 宽度缩略比例
            int rateWidth = width / WIDTH;
            // 高度缩略比
            int rateHeight = height / HEIGHT;

            // 宽度缩略比例大于高度缩略比例，使用宽度缩略比例
            int rate = rateWidth > rateHeight ? rateWidth : rateHeight;

            // 计算缩略图最终的宽度和高度
            int newWidth = width / rate;
            int newHeight = height / rate;

            BufferedImage bufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            bufferedImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0,
                    0, null);

            String imageType = file.getContentType().substring(file.getContentType().indexOf("/") + 1);
            ImageIO.write(bufferedImage, imageType, os);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return uploadPath + "/thum_" + file.getOriginalFilename();
    }
}
