@Override public String watermake(File imageFile,String imageFileName,String uploadPath,String realUploadPath){
  String logoFileName="logo_" + imageFileName;
  OutputStream os=null;
  try {
    Image image=ImageIO.read(imageFile);
    int width=image.getWidth(null);
    int height=image.getHeight(null);
    BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    Graphics2D g=bufferedImage.createGraphics();
    g.drawImage(image,0,0,width,height,null);
    String logoPath=realUploadPath + "/" + LOGO;
    File logo=new File(logoPath);
    Image imageLogo=ImageIO.read(logo);
    int markWidth=imageLogo.getWidth(null);
    int markHeight=imageLogo.getHeight(null);
    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
    g.rotate(Math.toRadians(30),bufferedImage.getWidth() / 2,bufferedImage.getHeight() / 2);
    int x=-width / 2;
    int y=-height / 2;
    int xmove=200;
    int ymove=200;
    double count=1.5;
    while (x < width * count) {
      y=-height / 2;
      while (y < height * count) {
        g.drawImage(imageLogo,x,y,null);
        y+=markHeight + ymove;
      }
      x+=markWidth + xmove;
    }
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
