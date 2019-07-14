@Override public Long read(ScanBuffer buffer){
  return buffer.getLong() + Long.MIN_VALUE;
}
