public void openSearchField(String text,boolean animated){
  if (menu == null || text == null) {
    return;
  }
  menu.openSearchField(!isSearchFieldVisible,text,animated);
}
