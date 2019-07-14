@Override public Long createValue(){
  return lastNonce.incrementAndGet();
}
