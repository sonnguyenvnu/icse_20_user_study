@Override public void refresh(final @NonNull User freshUser){
  this.user.onNext(freshUser);
}
