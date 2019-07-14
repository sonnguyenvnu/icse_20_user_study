@Override public void onFailure(long id,@Nullable Drawable error,@Nullable Throwable throwable){
  for (int i=0; i < mListeners.length; i++) {
    if (mListeners[i] != null) {
      mListeners[i].onFailure(id,error,throwable);
    }
  }
}
