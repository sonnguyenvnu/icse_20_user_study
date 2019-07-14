public int getEndIndex(){
  return getMark(0).getToken().getIndex() + getTokenCount() - 1;
}
