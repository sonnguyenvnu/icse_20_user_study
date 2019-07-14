@NonNull static Uri getBlobBuilder(@NonNull Uri uri){
  boolean isSvg="svg".equalsIgnoreCase(MimeTypeMap.getFileExtensionFromUrl(uri.toString()));
  List<String> segments=uri.getPathSegments();
  if (isSvg) {
    Uri svgBlob=Uri.parse(uri.toString().replace("blob/",""));
    return svgBlob.buildUpon().authority(RAW_AUTHORITY).build();
  }
  Uri.Builder urlBuilder=new Uri.Builder();
  String owner=segments.get(0);
  String repo=segments.get(1);
  String branch=segments.get(3);
  urlBuilder.scheme("https").authority(API_AUTHORITY).appendPath("repos").appendPath(owner).appendPath(repo).appendPath("contents");
  for (int i=4; i < segments.size(); i++) {
    urlBuilder.appendPath(segments.get(i));
  }
  if (uri.getQueryParameterNames() != null) {
    for (    String query : uri.getQueryParameterNames()) {
      urlBuilder.appendQueryParameter(query,uri.getQueryParameter(query));
    }
  }
  if (uri.getEncodedFragment() != null) {
    urlBuilder.encodedFragment(uri.getEncodedFragment());
  }
  urlBuilder.appendQueryParameter("ref",branch);
  return urlBuilder.build();
}
