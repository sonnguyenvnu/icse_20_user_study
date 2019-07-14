protected void updateTexture(PImage img,Texture tex){
  if (tex != null) {
    if (img.isModified()) {
      int x=img.getModifiedX1();
      int y=img.getModifiedY1();
      int w=img.getModifiedX2() - x;
      int h=img.getModifiedY2() - y;
      tex.set(img.pixels,x,y,w,h,img.format);
    }
  }
  img.setModified(false);
}
