private void showChangelog(String html){
  if (prettifyWebView == null)   return;
  webProgress.setVisibility(View.GONE);
  if (html != null) {
    message.setVisibility(View.GONE);
    prettifyWebView.setVisibility(View.VISIBLE);
    prettifyWebView.setGithubContent(html,null,false,false);
    prettifyWebView.setNestedScrollingEnabled(false);
  }
}
