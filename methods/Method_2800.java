@Override public double nextDouble(){
  ensureAvailableBytes(8);
  return super.nextDouble();
}
