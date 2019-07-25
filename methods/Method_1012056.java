public void activate(){
  if (getSearchHistory() != null && getSearchHistory().getSearches().size() != 0) {
    for (int i=getSearchHistory().getSearches().size() - 1; i >= 0; i--) {
      myText.addValue(getSearchHistory().getSearches().get(i));
    }
  }
  revalidate();
  setVisible(true);
  myText.requestFocus();
}
