@Override public void webViewOnPageStarted(final @NonNull KSWebViewClient webViewClient,final @Nullable String url){
  this.loadingIndicatorView.startAnimation(AnimationUtils.INSTANCE.appearAnimation());
}
