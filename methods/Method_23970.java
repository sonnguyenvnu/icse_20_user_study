protected boolean getDepthTest(){
  intBuffer.rewind();
  getBooleanv(DEPTH_TEST,intBuffer);
  return intBuffer.get(0) == 0 ? false : true;
}
