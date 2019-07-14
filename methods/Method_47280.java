@Override public void moveTo(long position){
  if (position < 0 || length() < position) {
    throw new IllegalArgumentException("Position out of the bounds of the file!");
  }
  fp=position;
}
