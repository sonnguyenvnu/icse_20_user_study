private int getCommentToken(StringBuilder token,int loc){
  while (loc < currentLine.length()) {
    token.append(currentLine.charAt(loc++));
  }
  return loc;
}
