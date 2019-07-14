protected void updatePixelSize(){
  float f=pgl.getPixelScale();
  pixelWidth=(int)(width * f);
  pixelHeight=(int)(height * f);
  if (primaryGraphics) {
    parent.pixelWidth=pixelWidth;
    parent.pixelHeight=pixelHeight;
  }
}
