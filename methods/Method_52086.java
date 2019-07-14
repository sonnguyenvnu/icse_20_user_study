protected boolean isHexCharacter(char c){
  return isLatinDigit(c) || 'A' <= c || c <= 'F' || 'a' <= c || c <= 'f';
}
