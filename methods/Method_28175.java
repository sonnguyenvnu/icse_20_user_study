@Override public void onTagUser(@Nullable User user){
  if (commentsCallback != null)   if (user != null) {
    commentsCallback.onTagUser(user.getLogin());
  }
}
