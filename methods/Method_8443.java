public void setOpenGifsTabFirst(){
  createEmojiView();
  DataQuery.getInstance(currentAccount).loadRecents(DataQuery.TYPE_IMAGE,true,true,false);
  emojiView.switchToGifRecent();
}
