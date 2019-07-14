public String remoteUrl(final String uri){
  String relative=nullToEmpty(uri.replaceFirst(this.localBase,""));
  return join(remoteBase,relative);
}
