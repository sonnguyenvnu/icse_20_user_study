private void writeIndentedLine(){
  if (buf.length() == 0) {
    if (startFlag)     startFlag=elseFlag=false;
    return;
  }
  if (startFlag) {
    boolean indentMore=!buf.toString().matches("[\\s\\]\\}\\)]+;") && (buf.charAt(0) != '{' || arrayLevel >= 0) && overflowFlag;
    if (indentMore) {
      tabs++;
      if (arrayIndent > 0)       tabs+=arrayIndent;
    }
    printIndentation();
    startFlag=false;
    if (indentMore) {
      tabs--;
      if (arrayIndent > 0)       tabs-=arrayIndent;
    }
  }
  if (lastNonSpaceChar() == '}' && bufStarts("else")) {
    result.append(' ');
  }
  if (elseFlag) {
    if (lastNonSpaceChar() == '}') {
      trimRight(result);
      result.append(' ');
    }
    elseFlag=false;
  }
  overflowFlag=inStatementFlag;
  arrayIndent=arrayLevel;
  result.append(buf);
  buf.setLength(0);
}
