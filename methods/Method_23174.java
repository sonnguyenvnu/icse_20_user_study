@Override public void copy(PImage src,int sx,int sy,int sw,int sh,int dx,int dy,int dw,int dh){
  g2.drawImage((Image)src.getNative(),dx,dy,dx + dw,dy + dh,sx,sy,sx + sw,sy + sh,null);
}
