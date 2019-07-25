@Override public int access(String s,int mask){
  ensureNotClosed();
  debug("ACCESS %s, mask %d",s,mask);
  return 0;
}
