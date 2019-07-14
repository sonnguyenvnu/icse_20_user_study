@Override public void getShaderPrecisionFormat(int shaderType,int precisionType,IntBuffer range,IntBuffer precision){
  gl2.glGetShaderPrecisionFormat(shaderType,precisionType,range,precision);
}
