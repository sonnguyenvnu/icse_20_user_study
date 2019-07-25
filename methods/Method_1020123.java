/** 
 * ??????
 * @param image
 * @param request
 * @return
 */
@PostMapping("/morewatermark") public ModelAndView morewatermark(List<MultipartFile> image,HttpServletRequest request){
  ModelAndView mav=new ModelAndView("/morewatermark");
  String uploadPath="static/images/";
  String realUploadPath=getClass().getClassLoader().getResource(uploadPath).getPath();
  logger.info("???????{}",uploadPath);
  logger.info("???????{}",realUploadPath);
  if (image != null && image.size() > 0) {
    List<PicInfo> picInfoList=new ArrayList<PicInfo>();
    for (    MultipartFile imageFileTemp : image) {
      if (imageFileTemp == null || imageFileTemp.getSize() < 1) {
        continue;
      }
      PicInfo picInfo=new PicInfo();
      String imageURL=uploadService.uploadImage(imageFileTemp,uploadPath,realUploadPath);
      File imageFile=new File(realUploadPath + imageFileTemp.getOriginalFilename());
      String logoImageURL=markService.watermake(imageFile,imageFileTemp.getOriginalFilename(),uploadPath,realUploadPath);
      picInfo.setImageUrl(imageURL);
      picInfo.setLogoImageUrl(logoImageURL);
      picInfoList.add(picInfo);
    }
    mav.addObject("picInfoList",picInfoList);
  }
  return mav;
}
