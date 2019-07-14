@Override public float nextFloat(){
  ensureAvailableBytes(4);
  return super.nextFloat();
}
