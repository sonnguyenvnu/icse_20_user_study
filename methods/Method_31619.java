@Override public int read() throws IOException {
  int read=super.read();
  if (read != -1 && !paused) {
    recorder.record((char)read);
  }
  return read;
}
