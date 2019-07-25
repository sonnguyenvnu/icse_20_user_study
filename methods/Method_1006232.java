private int peek() throws IOException {
  int c=read();
  unread(c);
  return c;
}
