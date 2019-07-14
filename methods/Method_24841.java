private static int offsetToLine(String text,int start,int offset){
  int line=0;
  while (offset >= start) {
    offset=text.lastIndexOf('\n',offset - 1);
    line++;
  }
  return line - 1;
}
