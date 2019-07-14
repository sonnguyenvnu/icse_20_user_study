@Override public void copy(PImage src,int sx,int sy,int sw,int sh,int dx,int dy,int dw,int dh){
  boolean needEndDraw=false;
  if (!drawing) {
    beginDraw();
    needEndDraw=true;
  }
  flush();
  Texture tex=getTexture(src);
  boolean invX=tex.invertedX();
  boolean invY=tex.invertedY();
  int scrX0, scrX1;
  int scrY0, scrY1;
  if (invX) {
    scrX0=dx + dw;
    scrX1=dx;
  }
 else {
    scrX0=dx;
    scrX1=dx + dw;
  }
  int texX0=sx;
  int texX1=sx + sw;
  int texY0, texY1;
  if (invY) {
    scrY0=height - (dy + dh);
    scrY1=height - dy;
    texY0=tex.height - (sy + sh);
    texY1=tex.height - sy;
  }
 else {
    scrY0=height - dy;
    scrY1=height - (dy + dh);
    texY0=sy;
    texY1=sy + sh;
  }
  pgl.drawTexture(tex.glTarget,tex.glName,tex.glWidth,tex.glHeight,0,0,width,height,texX0,texY0,texX1,texY1,scrX0,scrY0,scrX1,scrY1);
  if (needEndDraw) {
    endDraw();
  }
}
