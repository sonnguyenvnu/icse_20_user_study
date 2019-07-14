private void readComment(final String text){
  final int len=text.length();
  for (pos++; pos < len; pos++)   if (text.charAt(pos) == '\n') {
    return;
  }
}
