@Override public void onTagUser(@Nullable User user){
  if (commentsCallback != null && user != null) {
    commentsCallback.onTagUser(user.getLogin());
  }
}
