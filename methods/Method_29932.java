private void viewOnWeb(){
  startActivity(WebViewActivity.makeIntent(makeUrl(),true,getActivity()));
}
