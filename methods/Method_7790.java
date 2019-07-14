public int getItemPosition(int i){
  if (searchResultBotContext != null && searchResultBotContextSwitch != null) {
    i--;
  }
  return i;
}
