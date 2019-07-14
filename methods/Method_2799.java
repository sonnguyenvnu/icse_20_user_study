@Override public char nextChar(){
  ensureAvailableBytes(2);
  return super.nextChar();
}
