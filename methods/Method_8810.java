private int linkProgram(int program){
  GLES20.glLinkProgram(program);
  int[] linkStatus=new int[1];
  GLES20.glGetProgramiv(program,GLES20.GL_LINK_STATUS,linkStatus,0);
  if (linkStatus[0] == GLES20.GL_FALSE) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e(GLES20.glGetProgramInfoLog(program));
    }
  }
  return linkStatus[0];
}
