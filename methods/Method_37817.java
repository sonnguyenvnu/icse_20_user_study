@Override public char charAt(final int index){
  if (index < 0) {
    throw new IndexOutOfBoundsException();
  }
  return buffer[off + index];
}
