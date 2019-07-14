@Override public void onItemClicked(@NonNull SearchCodeModel item){
  if (item.getUrl() != null) {
    SchemeParser.launchUri(getContext(),item.getHtmlUrl());
  }
 else {
    showErrorMessage(getString(R.string.no_url));
  }
}
