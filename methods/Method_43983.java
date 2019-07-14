@Override public Long createValue(){
  return System.currentTimeMillis() / 1000 + validTime;
}
