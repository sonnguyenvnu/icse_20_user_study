@Override protected void ensureCapacity(long maximumSize){
  super.ensureCapacity(maximumSize);
  period=(maximumSize == 0) ? 10 : (10 * table.length);
  if (period <= 0) {
    period=Integer.MAX_VALUE;
  }
}
