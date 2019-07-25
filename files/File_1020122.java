package com.myimooc.java.watermark.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.myimooc.java.watermark.domain.PicInfo;
import com.myimooc.java.watermark.service.MarkService;
import com.myimooc.java.watermark.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * WatermarkController 控制类
 * 
 * @author ZhangCheng on 2017-07-21
 *
 */
@Controller
public class WatermarkController {

	private static Logger logger = LoggerFactory.getLogger(WatermarkController.class);

	@Autowired
	private UploadService uploadService;

	@Autowired
	private MarkService markService;

	/***
	 * �?�图片上传
	 * 
	 * @param image
	 * @param request
	 * @return
	 */
	@PostMapping("/watermark")
	public ModelAndView watermark(MultipartFile image, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/watermark");
		PicInfo picInfo = new PicInfo();

		String uploadPath = "static/images/";
		String realUploadPath = getClass().getClassLoader().getResource(uploadPath).getPath();

		logger.info("上传相对目录：{}", uploadPath);
		logger.info("上传�?对目录：{}", uploadPath);

		String imageURL = uploadService.uploadImage(image, uploadPath, realUploadPath);

		File imageFile = new File(realUploadPath + image.getOriginalFilename());

		String logoImageURL = markService.watermake(imageFile, image.getOriginalFilename(), uploadPath, realUploadPath);

		picInfo.setImageUrl(imageURL);
		picInfo.setLogoImageUrl(logoImageURL);
		mav.addObject("picInfo", picInfo);

		return mav;
	}

	/**
	 * 图片批�?上传
	 * 
	 * @param image
	 * @param request
	 * @return
	 */
	@PostMapping("/morewatermark")
	public ModelAndView morewatermark(List<MultipartFile> image, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/morewatermark");

		String uploadPath = "static/images/";
		String realUploadPath = getClass().getClassLoader().getResource(uploadPath).getPath();

		logger.info("上传相对目录：{}", uploadPath);
		logger.info("上传�?对目录：{}", realUploadPath);

		if (image != null && image.size() > 0) {
			List<PicInfo> picInfoList = new ArrayList<PicInfo>();
			for (MultipartFile imageFileTemp : image) {
				if(imageFileTemp == null || imageFileTemp.getSize() < 1){
					continue;
				}
				PicInfo picInfo = new PicInfo();
				String imageURL = uploadService.uploadImage(imageFileTemp, uploadPath, realUploadPath);
				File imageFile = new File(realUploadPath + imageFileTemp.getOriginalFilename());
				String logoImageURL = markService.watermake(imageFile, imageFileTemp.getOriginalFilename(), uploadPath,
						realUploadPath);
				picInfo.setImageUrl(imageURL);
				picInfo.setLogoImageUrl(logoImageURL);
				picInfoList.add(picInfo);
			}
			mav.addObject("picInfoList", picInfoList);
		}
		return mav;
	}
}
