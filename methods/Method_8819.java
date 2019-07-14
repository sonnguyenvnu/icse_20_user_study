public static int generateTexture(Size size){
  int texture;
  int[] textures=new int[1];
  GLES20.glGenTextures(1,textures,0);
  texture=textures[0];
  GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,texture);
  GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_WRAP_S,GLES20.GL_CLAMP_TO_EDGE);
  GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_WRAP_T,GLES20.GL_CLAMP_TO_EDGE);
  GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);
  GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_LINEAR);
  int width=(int)size.width;
  int height=(int)size.height;
  int format=GLES20.GL_RGBA;
  int type=GLES20.GL_UNSIGNED_BYTE;
  GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D,0,format,width,height,0,format,type,null);
  return texture;
}
