@Override public void webViewPageIntercepted(final @NonNull KSWebViewClient webViewClient,final @NonNull String url){
  this.viewModel.inputs.pageIntercepted(url);
}
