private boolean shouldWrite(User user,Context context){
  if (user.isOneself()) {
    ToastUtils.show(R.string.user_follow_error_cannot_follow_oneself,context);
    return false;
  }
 else {
    return true;
  }
}
