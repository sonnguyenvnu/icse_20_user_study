@PostMapping("thumbnail") public ModelAndView thumbnail(MultipartFile image,HttpServletRequest request){
  ModelAndView mav=new ModelAndView();
  String uploadPath="static/images/";
  String realUploadPath=getClass().getClassLoader().getResource(uploadPath).getPath();
  logger.info("???????{}",uploadPath);
  logger.info("???????{}",uploadPath);
  String imageUrl=uploadService.uploadImage(image,uploadPath,realUploadPath);
  String thumImageUrl=thumbnailService.thumbnail(image,uploadPath,realUploadPath);
  mav.addObject("imageUrl",imageUrl);
  mav.addObject("thumImageUrl",thumImageUrl);
  mav.setViewName("thumbnail");
  return mav;
}
