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
    g.setFont(new Font(FONT_NAME,FONT_STYLE,FONT_SIZE));
    g.setColor(FONT_COLOR);
    int markWidth=FONT_SIZE * getTextLength(MARK_TEXT);
    int markHeight=FONT_SIZE;
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
        g.drawString(MARK_TEXT,x,y);
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
