@Override public void clear() throws IOException {
  long count=available();
  while (count-- > 0) {
    if (receive(100,true) == null)     break;
  }
}
