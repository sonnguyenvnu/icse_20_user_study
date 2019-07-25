@Override public void seek(long position){
  ensureCapacity(position);
  count=(int)position;
}
