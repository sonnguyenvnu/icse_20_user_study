@Override public void startLine(int lineNumber){
  if (maxLineNumber > 0) {
    stringBuffer.append(lineNumberBeginPrefix);
    if (lineNumber == UNKNOWN_LINE_NUMBER) {
      stringBuffer.append(unknownLineNumberPrefix);
    }
 else {
      int left=0;
      left=printDigit(5,lineNumber,10000,left);
      left=printDigit(4,lineNumber,1000,left);
      left=printDigit(3,lineNumber,100,left);
      left=printDigit(2,lineNumber,10,left);
      stringBuffer.append((char)('0' + (lineNumber - left)));
    }
    stringBuffer.append(lineNumberEndPrefix);
  }
  for (int i=0; i < indentationCount; i++) {
    stringBuffer.append(TAB);
  }
}
