@Override public void onItemClicked(@NonNull Issue item){
  SchemeParser.launchUri(getContext(),item.getHtmlUrl());
}
