private boolean handleProjectUriRequest(final @NonNull Request request,final @NonNull WebView webView){
  this.viewModel.inputs.projectUriRequest(request);
  return false;
}
