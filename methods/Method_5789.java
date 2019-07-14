@Override public long open(DataSpec dataSpec) throws IOException {
  transferInitializing(dataSpec);
  this.dataSpec=dataSpec;
  Uri uri=dataSpec.uri;
  String scheme=uri.getScheme();
  if (!SCHEME_DATA.equals(scheme)) {
    throw new ParserException("Unsupported scheme: " + scheme);
  }
  String[] uriParts=Util.split(uri.getSchemeSpecificPart(),",");
  if (uriParts.length != 2) {
    throw new ParserException("Unexpected URI format: " + uri);
  }
  String dataString=uriParts[1];
  if (uriParts[0].contains(";base64")) {
    try {
      data=Base64.decode(dataString,0);
    }
 catch (    IllegalArgumentException e) {
      throw new ParserException("Error while parsing Base64 encoded string: " + dataString,e);
    }
  }
 else {
    data=Util.getUtf8Bytes(URLDecoder.decode(dataString,C.ASCII_NAME));
  }
  transferStarted(dataSpec);
  return data.length;
}
