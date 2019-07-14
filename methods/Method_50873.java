private int parseString(StringBuilder token,int loc,char stringDelimiter){
  boolean escaped=false;
  boolean done=false;
  char tok=' ';
  while (loc < currentLine.length() && !done) {
    tok=currentLine.charAt(loc);
    if (escaped && tok == stringDelimiter) {
      escaped=false;
    }
 else     if (tok == stringDelimiter && token.length() > 0) {
      done=true;
    }
 else     if (tok == '\\') {
      escaped=true;
    }
 else {
      escaped=false;
    }
    token.append(tok);
    loc++;
  }
  if (!done && loc >= currentLine.length() && spanMultipleLinesString && lineNumber < code.size() - 1) {
    if (spanMultipleLinesLineContinuationCharacter != null && token.length() > 0 && token.charAt(token.length() - 1) == spanMultipleLinesLineContinuationCharacter.charValue()) {
      token.deleteCharAt(token.length() - 1);
    }
    currentLine=code.get(++lineNumber);
    loc=parseString(token,0,stringDelimiter);
  }
  return loc + 1;
}
