@Override protected final void onChildSourceInfoRefreshed(MediaSourceHolder mediaSourceHolder,MediaSource mediaSource,Timeline timeline,@Nullable Object manifest){
  updateMediaSourceInternal(mediaSourceHolder,timeline);
}
