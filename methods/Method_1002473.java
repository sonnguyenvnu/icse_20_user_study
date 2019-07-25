@Override public void write(int b) throws IOException {
  wholeTextBuilder.append((char)b);
  if (b == '\n') {
    LOG.lifecycle("[checker] {}",lineTextBuilder.toString());
    processLine(lineTextBuilder.toString());
    lineTextBuilder=new StringBuilder();
  }
 else {
    lineTextBuilder.append((char)b);
  }
}
