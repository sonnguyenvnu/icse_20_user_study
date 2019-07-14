private static boolean containsUrls(CharSequence message){
  if (message == null || message.length() < 2 || message.length() > 1024 * 20) {
    return false;
  }
  int length=message.length();
  int digitsInRow=0;
  int schemeSequence=0;
  int dotSequence=0;
  char lastChar=0;
  for (int i=0; i < length; i++) {
    char c=message.charAt(i);
    if (c >= '0' && c <= '9') {
      digitsInRow++;
      if (digitsInRow >= 6) {
        return true;
      }
      schemeSequence=0;
      dotSequence=0;
    }
 else     if (!(c != ' ' && digitsInRow > 0)) {
      digitsInRow=0;
    }
    if ((c == '@' || c == '#' || c == '/' || c == '$') && i == 0 || i != 0 && (message.charAt(i - 1) == ' ' || message.charAt(i - 1) == '\n')) {
      return true;
    }
    if (c == ':') {
      if (schemeSequence == 0) {
        schemeSequence=1;
      }
 else {
        schemeSequence=0;
      }
    }
 else     if (c == '/') {
      if (schemeSequence == 2) {
        return true;
      }
      if (schemeSequence == 1) {
        schemeSequence++;
      }
 else {
        schemeSequence=0;
      }
    }
 else     if (c == '.') {
      if (dotSequence == 0 && lastChar != ' ') {
        dotSequence++;
      }
 else {
        dotSequence=0;
      }
    }
 else     if (c != ' ' && lastChar == '.' && dotSequence == 1) {
      return true;
    }
 else {
      dotSequence=0;
    }
    lastChar=c;
  }
  return false;
}
