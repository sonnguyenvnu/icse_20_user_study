protected void initTexture(int target,int format,int width,int height,int initColor){
  int[] glcolor=new int[16 * 16];
  Arrays.fill(glcolor,javaToNativeARGB(initColor));
  IntBuffer texels=allocateDirectIntBuffer(16 * 16);
  texels.put(glcolor);
  texels.rewind();
  for (int y=0; y < height; y+=16) {
    int h=PApplet.min(16,height - y);
    for (int x=0; x < width; x+=16) {
      int w=PApplet.min(16,width - x);
      texSubImage2D(target,0,x,y,w,h,format,UNSIGNED_BYTE,texels);
    }
  }
}
