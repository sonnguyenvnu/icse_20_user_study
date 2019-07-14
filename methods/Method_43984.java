@Override public Long createValue(){
  lastNonce=Math.max(lastNonce + 1,(int)((System.currentTimeMillis() - START_MILLIS) / 250L));
  return (long)lastNonce;
}
