@Override public Long createValue(){
  long newNonce=System.currentTimeMillis() * 1000;
  while (newNonce <= lastNonce) {
    newNonce++;
  }
  lastNonce=newNonce;
  return newNonce;
}
