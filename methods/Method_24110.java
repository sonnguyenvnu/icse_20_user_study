@Override protected void clipImpl(float x1,float y1,float x2,float y2){
  flush();
  pgl.enable(PGL.SCISSOR_TEST);
  float h=y2 - y1;
  clipRect[0]=(int)x1;
  clipRect[1]=(int)(height - y1 - h);
  clipRect[2]=(int)(x2 - x1);
  clipRect[3]=(int)h;
  pgl.scissor(clipRect[0],clipRect[1],clipRect[2],clipRect[3]);
  clip=true;
}
