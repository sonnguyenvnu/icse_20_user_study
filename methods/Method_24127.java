protected void textCharModelImpl(FontTexture.TextureInfo info,float x0,float y0,float x1,float y1){
  beginShape(QUADS);
  texture(textTex.getTexture(info));
  vertex(x0,y0,info.u0,info.v0);
  vertex(x1,y0,info.u1,info.v0);
  vertex(x1,y1,info.u1,info.v1);
  vertex(x0,y1,info.u0,info.v1);
  endShape();
}
