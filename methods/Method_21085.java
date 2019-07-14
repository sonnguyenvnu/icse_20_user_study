private boolean handleCheckoutThanksUriRequest(final @NonNull Request request,final @NonNull WebView webView){
  final Intent intent=new Intent(this,ThanksActivity.class).putExtra(IntentKey.PROJECT,this.project);
  startActivityWithTransition(intent,R.anim.slide_in_right,R.anim.fade_out_slide_out_left);
  return true;
}
