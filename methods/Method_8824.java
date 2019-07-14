public static void HasGLError(){
  int error=GLES20.glGetError();
  if (error != 0) {
    Log.d("Paint",GLUtils.getEGLErrorString(error));
  }
}
