@Override public void reset(){
  if (mem_.getCapacity() >= COMBINED_BUFFER) {
    mem_.putByte(FLAGS_BYTE,(byte)EMPTY_FLAG_MASK);
    mem_.putLong(N_LONG,0L);
    mem_.putDouble(MIN_DOUBLE,Double.NaN);
    mem_.putDouble(MAX_DOUBLE,Double.NaN);
  }
}
