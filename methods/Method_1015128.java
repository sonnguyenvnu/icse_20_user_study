@Override public long remaining(){
  return remaining + buffer.remaining();
}
