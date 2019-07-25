public String thumbnail(MultipartFile file,String uploadPath,String realUploadPath){
  try {
    String des=realUploadPath + "/thum_" + file.getOriginalFilename();
    Thumbnails.of(file.getInputStream()).size(WIDTH,HEIGHT).toFile(des);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return uploadPath + "/thum_" + file.getOriginalFilename();
}
