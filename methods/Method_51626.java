private int getPosition(String[] lines,int line,int column){
  int pos=0;
  for (int count=0; count < lines.length; ) {
    String tok=lines[count++];
    if (count == line) {
      int linePos=0;
      int i;
      for (i=0; linePos < column && linePos < tok.length(); i++) {
        linePos++;
        if (tok.charAt(i) == '\t') {
          linePos--;
          linePos+=8 - (linePos & 07);
        }
      }
      return pos + i - 1;
    }
    pos+=tok.length() + 1;
  }
  throw new RuntimeException("Line " + line + " not found");
}
