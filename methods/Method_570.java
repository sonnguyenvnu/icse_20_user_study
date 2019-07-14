public final boolean isBlankInput(){
  for (int i=0; ; ++i) {
    char chLocal=buf[i];
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
