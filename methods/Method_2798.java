@Override public int nextInt(){
  ensureAvailableBytes(4);
  return super.nextInt();
}
