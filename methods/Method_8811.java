private void destroyShader(int vertexShader,int fragmentShader,int program){
  if (vertexShader != 0) {
    GLES20.glDeleteShader(vertexShader);
  }
  if (fragmentShader != 0) {
    GLES20.glDeleteShader(fragmentShader);
  }
  if (program != 0) {
    GLES20.glDeleteProgram(vertexShader);
  }
}
