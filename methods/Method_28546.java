@NonNull private static String getLinkBaseUrl(@NonNull String baseUrl){
  NameParser nameParser=new NameParser(baseUrl);
  String owner=nameParser.getUsername();
  String repoName=nameParser.getName();
  Uri uri=Uri.parse(baseUrl);
  ArrayList<String> paths=new ArrayList<>(uri.getPathSegments());
  StringBuilder builder=new StringBuilder();
  builder.append("https://").append(uri.getAuthority()).append("/").append(owner).append("/").append(repoName).append("/");
  boolean containsMaster=paths.size() > 3 && paths.get(2).equalsIgnoreCase("blob");
  if (!containsMaster) {
    builder.append("blob/master/");
  }
  paths.remove(owner);
  paths.remove(repoName);
  for (  String path : paths) {
    if (!path.equalsIgnoreCase(uri.getLastPathSegment())) {
      builder.append(path).append("/");
    }
  }
  return builder.toString();
}
