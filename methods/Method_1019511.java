@Override public void reset(){
  final int arrLongs=1 << getLgArrLongs();
  final int preambleLongs=mem_.getByte(PREAMBLE_LONGS_BYTE) & 0X3F;
  final int preBytes=preambleLongs << 3;
  mem_.clear(preBytes,arrLongs * 8L);
  mem_.putByte(FLAGS_BYTE,(byte)EMPTY_FLAG_MASK);
  mem_.putInt(RETAINED_ENTRIES_INT,0);
  final float p=mem_.getFloat(P_FLOAT);
  final long thetaLong=(long)(p * MAX_THETA_LONG_AS_DOUBLE);
  mem_.putLong(THETA_LONG,thetaLong);
}
