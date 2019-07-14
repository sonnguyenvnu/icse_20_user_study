private int getTokenFromLine(StringBuilder token,int loc){
  for (int j=loc; j < currentLine.length(); j++) {
    char tok=currentLine.charAt(j);
    if (!Character.isWhitespace(tok) && !ignoreCharacter(tok)) {
      if (isComment(tok)) {
        if (token.length() > 0) {
          return j;
        }
 else {
          return getCommentToken(token,loc);
        }
      }
 else       if (isString(tok)) {
        if (token.length() > 0) {
          return j;
        }
 else {
          return parseString(token,j,tok);
        }
      }
 else {
        token.append(tok);
      }
    }
 else {
      if (token.length() > 0) {
        return j;
      }
    }
    loc=j;
  }
  return loc + 1;
}
