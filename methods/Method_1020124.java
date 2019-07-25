@Override public String watermake(File imageFile,String imageFileName,String uploadPath,String realUploadPath){
  String logoFileName="logo_" + imageFileName;
  OutputStream os=null;
  String logoPath=realUploadPath + "/" + LOGO;
  try {
    Image image=ImageIO.read(imageFile);
    int width=image.getWidth(null);
    int height=image.getHeight(null);
    BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    Graphics2D g=bufferedImage.createGraphics();
    g.drawImage(image,0,0,width,height,null);
    File logo=new File(logoPath);
    Image imageLogo=ImageIO.read(logo);
    int markWidth=imageLogo.getWidth(null);
    int markHeight=imageLogo.getHeight(null);
    int widthDiff=width - markWidth;
    int heightDiff=height - markHeight;
    int x=X;
    int y=Y;
    if (x > widthDiff) {
      x=widthDiff;
    }
    if (y > heightDiff) {
      y=heightDiff;
    }
    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
    g.drawImage(imageLogo,x,y,null);
    g.dispose();
    os=new FileOutputStream(realUploadPath + "/" + logoFileName);
    JPEGImageEncoder en=JPEGCodec.createJPEGEncoder(os);
    en.encode(bufferedImage);
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
  return uploadPath + "/" + logoFileName;
}
