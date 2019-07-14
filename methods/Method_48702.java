@Override public DataOutput getDataOutput(int initialCapacity){
  return new StandardDataOutput(initialCapacity);
}
