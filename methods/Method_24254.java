@Override public void getUniformfv(int program,int location,FloatBuffer params){
  gl2.glGetUniformfv(program,location,params);
}
