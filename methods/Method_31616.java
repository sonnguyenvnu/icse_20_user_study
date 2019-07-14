@Override public void mark(int readAheadLimit) throws IOException {
  paused=true;
  super.mark(readAheadLimit);
}
