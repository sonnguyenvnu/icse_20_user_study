private void saveNewPage(){
  if (pager == null) {
    return;
  }
  int newPage;
  int currentItem=pager.getCurrentItem();
  if (currentItem == 2) {
    newPage=1;
  }
 else   if (currentItem == 1) {
    newPage=2;
  }
 else {
    newPage=0;
  }
  if (currentPage != newPage) {
    currentPage=newPage;
    MessagesController.getGlobalEmojiSettings().edit().putInt("selected_page",newPage).commit();
  }
}
