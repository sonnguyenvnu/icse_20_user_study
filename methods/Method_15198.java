public boolean isOnFirstTab(){
  return getTabCount() <= 0 ? false : getCurrentTabPosition() <= 0;
}
