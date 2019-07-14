private boolean handleProjectUpdatesUriRequest(final @NonNull Request request,final @NonNull WebView webView){
  this.viewModel.inputs.goToUpdatesRequest(request);
  return false;
}
