@Override public void onSetMdText(@NonNull String text,String baseUrl,boolean replace){
  webView.setVisibility(View.VISIBLE);
  loader.setIndeterminate(false);
  webView.setGithubContentWithReplace(text,baseUrl,replace);
  webView.setOnContentChangedListener(this);
  getActivity().invalidateOptionsMenu();
}
