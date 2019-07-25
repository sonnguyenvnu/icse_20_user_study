public void clear(int mask){
  gc.setColor(clearcolor);
  gc.fillRect(0,0,width,height);
  gc.setColor(color);
  identity(matrix);
  identity(stack);
  for (int i=0; i < zbuffer.length; i++) {
    zbuffer[i]=-500;
  }
  boundTexture=false;
}
