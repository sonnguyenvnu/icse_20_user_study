/** 
 * Builds a GL shader program from vertex & fragment shader code.
 * @param vertexCode GLES20 vertex shader program.
 * @param fragmentCode GLES20 fragment shader program.
 * @return GLES20 program id.
 */
public static int compileProgram(String vertexCode,String fragmentCode){
  int program=GLES20.glCreateProgram();
  checkGlError();
  addShader(GLES20.GL_VERTEX_SHADER,vertexCode,program);
  addShader(GLES20.GL_FRAGMENT_SHADER,fragmentCode,program);
  GLES20.glLinkProgram(program);
  int[] linkStatus=new int[]{GLES20.GL_FALSE};
  GLES20.glGetProgramiv(program,GLES20.GL_LINK_STATUS,linkStatus,0);
  if (linkStatus[0] != GLES20.GL_TRUE) {
    throwGlError("Unable to link shader program: \n" + GLES20.glGetProgramInfoLog(program));
  }
  checkGlError();
  return program;
}
