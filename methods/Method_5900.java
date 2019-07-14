private static void addShader(int type,String source,int program){
  int shader=GLES20.glCreateShader(type);
  GLES20.glShaderSource(shader,source);
  GLES20.glCompileShader(shader);
  int[] result=new int[]{GLES20.GL_FALSE};
  GLES20.glGetShaderiv(shader,GLES20.GL_COMPILE_STATUS,result,0);
  if (result[0] != GLES20.GL_TRUE) {
    throwGlError(GLES20.glGetShaderInfoLog(shader) + ", source: " + source);
  }
  GLES20.glAttachShader(program,shader);
  GLES20.glDeleteShader(shader);
  checkGlError();
}
