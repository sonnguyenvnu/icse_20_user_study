private void onSend(){
  String text=mTextEdit.getText().toString();
  if (TextUtils.isEmpty(text) && mImageUris.isEmpty() && mLinkInfo == null) {
    ToastUtils.show(R.string.broadcast_send_error_empty,getActivity());
    return;
  }
  if (text.length() > Broadcast.MAX_TEXT_LENGTH) {
    ToastUtils.show(R.string.broadcast_send_error_text_too_long,getActivity());
    return;
  }
  send(text,mImageUris,mLinkInfo);
}
