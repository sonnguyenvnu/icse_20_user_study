@OnClick(R.id.insert) public void onInsertClicked(){
  if (callback != null) {
    callback.onAppendLink(InputHelper.toString(title),InputHelper.toString(link),isLink());
  }
  dismiss();
}
