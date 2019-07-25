@Override public URI build(){
  String string=toString();
  try {
    return new URI(string);
  }
 catch (  URISyntaxException e) {
    throw new InternalRatpackError("HttpUriBuilder produced invalid URI: " + toString(),e);
  }
}
