@Override public void extraLine(int count){
  if (realignmentLineNumber) {
    while (count-- > 0) {
      if (maxLineNumber > 0) {
        stringBuffer.append(lineNumberBeginPrefix);
        stringBuffer.append(unknownLineNumberPrefix);
        stringBuffer.append(lineNumberEndPrefix);
      }
      stringBuffer.append(NEWLINE);
    }
  }
}
