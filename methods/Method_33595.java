@Override protected void setTitleClickMore(){
  if (!TextUtils.isEmpty(mMoreUrl)) {
    WebViewActivity.loadUrl(this,mMoreUrl,mMoreTitle);
  }
 else {
    ToastUtil.showToast("???????~");
  }
}
