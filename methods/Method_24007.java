protected boolean compiled(int shader){
  intBuffer.rewind();
  getShaderiv(shader,COMPILE_STATUS,intBuffer);
  return intBuffer.get(0) == 0 ? false : true;
}
