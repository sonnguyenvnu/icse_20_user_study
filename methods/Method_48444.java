@Override public WriteBuffer putBoolean(boolean val){
  return putByte((byte)(val ? 1 : 0));
}
