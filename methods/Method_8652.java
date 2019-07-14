public void setStickersBanned(boolean value,int chatId){
  if (typeTabs == null) {
    return;
  }
  if (value) {
    currentChatId=chatId;
  }
 else {
    currentChatId=0;
  }
  View view=typeTabs.getTab(2);
  if (view != null) {
    view.setAlpha(currentChatId != 0 ? 0.5f : 1.0f);
    if (currentChatId != 0 && pager.getCurrentItem() != 0) {
      showBackspaceButton(true,true);
      showStickerSettingsButton(false,true);
      pager.setCurrentItem(0,false);
    }
  }
}
