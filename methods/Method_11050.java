public void setContent(String str){
  if (RxRegTool.isURL(str)) {
    mTvContent.setMovementMethod(LinkMovementMethod.getInstance());
    mTvContent.setText(RxTextTool.getBuilder("").setBold().append(str).setUrl(str).create());
  }
 else {
    mTvContent.setText(str);
  }
}
