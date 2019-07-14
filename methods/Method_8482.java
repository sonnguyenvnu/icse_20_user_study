public void addRecentGif(TLRPC.Document searchImage){
  DataQuery.getInstance(currentAccount).addRecentGif(searchImage,(int)(System.currentTimeMillis() / 1000));
  if (emojiView != null) {
    emojiView.addRecentGif(searchImage);
  }
}
