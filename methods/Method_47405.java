@Override public void moveTo(long position) throws IllegalArgumentException {
  if (position < 0 || length() < position) {
    throw new IllegalArgumentException("Position out of the bounds of the file!");
  }
  fp=position;
}
