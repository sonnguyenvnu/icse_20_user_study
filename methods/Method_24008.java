protected boolean linked(int program){
  intBuffer.rewind();
  getProgramiv(program,LINK_STATUS,intBuffer);
  return intBuffer.get(0) == 0 ? false : true;
}
