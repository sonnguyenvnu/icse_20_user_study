public final boolean matchField(char[] fieldName){
  for (; ; ) {
    if (!charArrayCompare(fieldName)) {
      if (isWhitespace(ch)) {
        next();
        continue;
      }
      return false;
    }
 else {
      break;
    }
  }
  bp=bp + fieldName.length;
  ch=charAt(bp);
  if (ch == '{') {
    next();
    token=JSONToken.LBRACE;
  }
 else   if (ch == '[') {
    next();
    token=JSONToken.LBRACKET;
  }
 else   if (ch == 'S' && charAt(bp + 1) == 'e' && charAt(bp + 2) == 't' && charAt(bp + 3) == '[') {
    bp+=3;
    ch=charAt(bp);
    token=JSONToken.SET;
  }
 else {
    nextToken();
  }
  return true;
}
