private boolean shouldWrite(Broadcast broadcast,Context context){
  if (broadcast.isAuthorOneself()) {
    ToastUtils.show(R.string.broadcast_like_error_cannot_like_oneself,context);
    return false;
  }
 else {
    return true;
  }
}
