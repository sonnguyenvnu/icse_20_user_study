private int getMask(){
  int mask=0;
  if (lastReturnedNum < searchResultMessages.size() - 1 || !messagesSearchEndReached[0] || !messagesSearchEndReached[1]) {
    mask|=1;
  }
  if (lastReturnedNum > 0) {
    mask|=2;
  }
  return mask;
}
