@SuppressLint("NewApi") private void androidPayComplete(final @NonNull FullWallet fullWallet){
  final AndroidPayAuthorizedPayload authorizedPayload=AndroidPayUtils.authorizedPayloadFromFullWallet(fullWallet,this.gson);
  final String json=this.gson.toJson(authorizedPayload,AndroidPayAuthorizedPayload.class);
  final String javascript=String.format("checkout_android_pay_next(%s);",json);
  this.webView.evaluateJavascript(javascript,null);
}
