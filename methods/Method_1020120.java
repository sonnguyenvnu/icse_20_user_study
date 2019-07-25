@SuppressWarnings("static-access") public String thumbnail(MultipartFile file,String uploadPath,String realUploadPath){
  OutputStream os=null;
  try {
    String des=realUploadPath + "/thum_" + file.getOriginalFilename();
    os=new FileOutputStream(des);
    Image image=ImageIO.read(file.getInputStream());
    int width=image.getWidth(null);
    int height=image.getHeight(null);
    int rateWidth=width / WIDTH;
    int rateHeight=height / HEIGHT;
    int rate=rateWidth > rateHeight ? rateWidth : rateHeight;
    int newWidth=width / rate;
    int newHeight=height / rate;
    BufferedImage bufferedImage=new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);
    bufferedImage.getGraphics().drawImage(image.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH),0,0,null);
    String imageType=file.getContentType().substring(file.getContentType().indexOf("/") + 1);
    ImageIO.write(bufferedImage,imageType,os);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
 finally {
    if (os != null) {
      try {
        os.close();
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
    }
  }
  return uploadPath + "/thum_" + file.getOriginalFilename();
}
