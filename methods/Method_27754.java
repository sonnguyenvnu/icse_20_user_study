@Override public void onClick(@NonNull String url){
  SchemeParser.launchUri(getContext(),Uri.parse(url),true);
}
