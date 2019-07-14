@Override public void webViewExternalLinkActivated(final @NonNull KSWebViewClient webViewClient,final @NonNull String url){
  this.viewModel.inputs.externalLinkActivated();
}
