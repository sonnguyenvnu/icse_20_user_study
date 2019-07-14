public void closeSearchField(boolean closeKeyboard){
  if (!isSearchFieldVisible || menu == null) {
    return;
  }
  menu.closeSearchField(closeKeyboard);
}
