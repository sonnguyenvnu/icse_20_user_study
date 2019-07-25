private WebTarget resource(){
  final WebTarget target=client.target(uri);
  if (!isNullOrEmpty(apiVersion)) {
    return target.path(apiVersion);
  }
  return target;
}
