@Override public void apply(BufferedImage image){
  Image fgImage=null;
  try {
    fgImage=ImageIO.read(fgFile);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  if (fgImage != null) {
    int width=image.getWidth();
    int height=image.getWidth();
    float scale=Math.min(width / (float)fgImage.getWidth(null),height / (float)fgImage.getHeight(null));
    if (addPadding)     scale=scale * (72f / 108);
    Image fgImageScaled=fgImage.getScaledInstance((int)(fgImage.getWidth(null) * scale),(int)(fgImage.getWidth(null) * scale),Image.SCALE_SMOOTH);
    Graphics2D g=image.createGraphics();
    if (addPadding)     g.drawImage(fgImageScaled,(int)(width * (1 - 72f / 108) / 2),(int)(height * (1 - 72f / 108) / 2),null);
 else     g.drawImage(fgImageScaled,0,0,null);
    g.dispose();
  }
}
