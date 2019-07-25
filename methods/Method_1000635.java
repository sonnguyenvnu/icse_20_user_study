@Override public int read() throws IOException {
  if (cursor > buffer.width) {
    index++;
    cursor=0;
  }
  if (index > buffer.index)   return -1;
  if (index < buffer.bytes.size()) {
    byte[] cs=buffer.bytes.get(index);
    if (cursor < buffer.cursor)     return cs[cursor++];
  }
  return -1;
}
