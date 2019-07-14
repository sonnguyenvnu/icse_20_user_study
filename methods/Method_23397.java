static private BufferedImage shrinkImage(BufferedImage img,int targetWidth,int targetHeight){
  int type=(img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
  BufferedImage outgoing=img;
  BufferedImage scratchImage=null;
  Graphics2D g2=null;
  int prevW=outgoing.getWidth();
  int prevH=outgoing.getHeight();
  boolean isTranslucent=img.getTransparency() != Transparency.OPAQUE;
  int w=img.getWidth();
  int h=img.getHeight();
  do {
    if (w > targetWidth) {
      w/=2;
      if (w < targetWidth) {
        w=targetWidth;
      }
    }
 else     if (targetWidth >= w) {
      w=targetWidth;
    }
    if (h > targetHeight) {
      h/=2;
      if (h < targetHeight) {
        h=targetHeight;
      }
    }
 else     if (targetHeight >= h) {
      h=targetHeight;
    }
    if (scratchImage == null || isTranslucent) {
      scratchImage=new BufferedImage(w,h,type);
      g2=scratchImage.createGraphics();
    }
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(outgoing,0,0,w,h,0,0,prevW,prevH,null);
    prevW=w;
    prevH=h;
    outgoing=scratchImage;
  }
 while (w != targetWidth || h != targetHeight);
  if (g2 != null) {
    g2.dispose();
  }
  if (targetWidth != outgoing.getWidth() || targetHeight != outgoing.getHeight()) {
    scratchImage=new BufferedImage(targetWidth,targetHeight,type);
    g2=scratchImage.createGraphics();
    g2.drawImage(outgoing,0,0,null);
    g2.dispose();
    outgoing=scratchImage;
  }
  return outgoing;
}
