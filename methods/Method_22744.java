private void readMLComment(final String text){
  final int len=text.length();
  for (pos++; pos < len; pos++) {
    final char c=text.charAt(pos);
    if (c == '*' && (pos < len - 1)) {
      final char d=text.charAt(pos + 1);
      if (d == '/') {
        pos++;
        return;
      }
    }
  }
}
