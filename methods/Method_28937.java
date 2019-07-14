public void writeCrLf() throws IOException {
  if (2 >= buf.length - count) {
    flushBuffer();
  }
  buf[count++]='\r';
  buf[count++]='\n';
}
