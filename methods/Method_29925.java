private void onShowCommentIme(){
  if (canSendComment()) {
    ImeUtils.showIme(mCommentEdit);
  }
 else {
    ToastUtils.show(R.string.broadcast_send_comment_disabled,getActivity());
  }
}
