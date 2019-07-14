private void onCurrentListChanged(@NonNull List<T> previousList,@Nullable Runnable commitCallback){
  for (  ListListener<T> listener : mListeners) {
    listener.onCurrentListChanged(previousList,mReadOnlyList);
  }
  if (commitCallback != null) {
    commitCallback.run();
  }
}
