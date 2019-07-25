@Override public long position(){
  return (size - remaining) + buffer.remaining();
}
