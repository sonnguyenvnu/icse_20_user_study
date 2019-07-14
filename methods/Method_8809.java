private CompilationResult compileShader(int type,String shaderCode){
  int shader=GLES20.glCreateShader(type);
  GLES20.glShaderSource(shader,shaderCode);
  GLES20.glCompileShader(shader);
  int[] compileStatus=new int[1];
  GLES20.glGetShaderiv(shader,GLES20.GL_COMPILE_STATUS,compileStatus,0);
  if (compileStatus[0] == GLES20.GL_FALSE) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e(GLES20.glGetShaderInfoLog(shader));
    }
  }
  return new CompilationResult(shader,compileStatus[0]);
}
