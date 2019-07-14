private void notifyRefresh(@Nonnull Key key){
  refreshSubject.onNext(key);
}
