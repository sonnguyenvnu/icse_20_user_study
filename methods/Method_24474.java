protected void copyObject(Texture src){
  dispose();
  width=src.width;
  height=src.height;
  glName=src.glName;
  glTarget=src.glTarget;
  glFormat=src.glFormat;
  glMinFilter=src.glMinFilter;
  glMagFilter=src.glMagFilter;
  glWidth=src.glWidth;
  glHeight=src.glHeight;
  usingMipmaps=src.usingMipmaps;
  usingRepeat=src.usingRepeat;
  maxTexcoordU=src.maxTexcoordU;
  maxTexcoordV=src.maxTexcoordV;
  invertedX=src.invertedX;
  invertedY=src.invertedY;
}
