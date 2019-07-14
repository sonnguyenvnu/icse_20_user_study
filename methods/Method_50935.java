private int countDuplicateTokens(TokenEntry mark1,TokenEntry mark2){
  int index=0;
  while (!matchEnded(ma.tokenAt(index,mark1),ma.tokenAt(index,mark2))) {
    index++;
  }
  return index;
}
