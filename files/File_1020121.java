package com.myimooc.java.thumbnail.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 缩略图�?务类
 * @author ZhangCheng on 2017-07-19
 *
 */
@Service
public class ThumbnailService {
	
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	
	public String thumbnail(MultipartFile file,String uploadPath,String realUploadPath){
		try{
			String des = realUploadPath + "/thum_" + file.getOriginalFilename();
			Thumbnails.of(file.getInputStream()).size(WIDTH, HEIGHT).toFile(des);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return uploadPath + "/thum_" + file.getOriginalFilename();
	}
}
