/** 
 * ?????
 * @param image
 * @param request
 * @return
 */
@PostMapping("/watermark") public ModelAndView watermark(MultipartFile image,HttpServletRequest request){
  ModelAndView mav=new ModelAndView("/watermark");
  PicInfo picInfo=new PicInfo();
  String uploadPath="static/images/";
  String realUploadPath=getClass().getClassLoader().getResource(uploadPath).getPath();
  logger.info("???????{}",uploadPath);
  logger.info("???????{}",uploadPath);
  String imageURL=uploadService.uploadImage(image,uploadPath,realUploadPath);
  File imageFile=new File(realUploadPath + image.getOriginalFilename());
  String logoImageURL=markService.watermake(imageFile,image.getOriginalFilename(),uploadPath,realUploadPath);
  picInfo.setImageUrl(imageURL);
  picInfo.setLogoImageUrl(logoImageURL);
  mav.addObject("picInfo",picInfo);
  return mav;
}
