private boolean shouldAddBasicAuthorizationHeader(final @NonNull Request request){
  if (this.currentUser.exists()) {
    return false;
  }
  final Uri initialRequestUri=Uri.parse(request.url().toString());
  return KSUri.isHivequeenUri(initialRequestUri,this.endpoint) || KSUri.isStagingUri(initialRequestUri,this.endpoint);
}
