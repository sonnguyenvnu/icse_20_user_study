@Override public int capacity(){
  return (int)((consumerMask + 2) >> 1);
}
