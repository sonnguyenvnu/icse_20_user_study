public boolean skip(final String s) throws IOException {
  if (s == null || s.length() == 0) {
    return false;
  }
  if (s.charAt(0) != this.mCurrent) {
    return false;
  }
  final int len=s.length();
  this.mStream.mark(len - 1);
  for (int n=1; n < len; n++) {
    final int value=this.mStream.read();
    if (value != s.charAt(n)) {
      this.mStream.reset();
      return false;
    }
  }
  return true;
}
