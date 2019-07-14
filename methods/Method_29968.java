private void onOk(){
  String url=mUrlEdit.getText().toString();
  if (!UrlUtils.isValidUrl(url)) {
    mUrlLayout.setError(getContext().getString(R.string.broadcast_send_edit_link_url_error));
    return;
  }
  String title=mTitleEdit.getText().toString();
  SendBroadcastFragment.LinkInfo linkInfo=new SendBroadcastFragment.LinkInfo(url,title,null,null);
  getListener().setLink(linkInfo);
  dismiss();
}
