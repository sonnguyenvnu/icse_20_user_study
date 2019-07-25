@Override public int read() throws IOException {
  if (pos < 0) {
    line=fixBibkey(readpreLine());
    pos=0;
    if (line == null) {
      return -1;
    }
  }
  if (pos >= line.length()) {
    pos=-1;
    return '\n';
  }
  return line.charAt(pos++);
}
