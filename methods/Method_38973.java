/** 
 * Calculates  {@link Position current position}: offset, line and column.
 */
protected Position position(final int position){
  int line;
  int offset;
  int lastNewLineOffset;
  if (position > lastOffset) {
    line=1;
    offset=0;
    lastNewLineOffset=0;
  }
 else {
    line=lastLine;
    offset=lastOffset;
    lastNewLineOffset=lastLastNewLineOffset;
  }
  while (offset < position) {
    final char c=input[offset];
    if (c == '\n') {
      line++;
      lastNewLineOffset=offset + 1;
    }
    offset++;
  }
  lastOffset=offset;
  lastLine=line;
  lastLastNewLineOffset=lastNewLineOffset;
  return new Position(position,line,position - lastNewLineOffset + 1);
}
