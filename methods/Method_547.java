public boolean isBlankInput(){
  for (int i=0; ; ++i) {
    char chLocal=charAt(i);
    if (chLocal == EOI) {
      token=JSONToken.EOF;
      break;
    }
    if (!isWhitespace(chLocal)) {
      return false;
    }
  }
  return true;
}
