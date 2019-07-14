@Override public void webViewOnPageFinished(final @NonNull KSWebViewClient webViewClient,final @Nullable String url){
  this.loadingIndicatorView.startAnimation(AnimationUtils.INSTANCE.disappearAnimation());
}
