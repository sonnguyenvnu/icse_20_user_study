@NonNull private static String parseReadme(@NonNull String source,@NonNull String baseUrl,boolean isWiki){
  NameParser nameParser=new NameParser(baseUrl);
  String owner=nameParser.getUsername();
  String repoName=nameParser.getName();
  Uri uri=Uri.parse(baseUrl);
  ArrayList<String> paths=new ArrayList<>(uri.getPathSegments());
  StringBuilder builder=new StringBuilder();
  builder.append(owner).append("/").append(repoName).append("/");
  boolean containsMaster=paths.size() > 3;
  if (!containsMaster) {
    builder.append("master/");
  }
 else {
    paths.remove("blob");
  }
  paths.remove(owner);
  paths.remove(repoName);
  for (  String path : paths) {
    if (!path.equalsIgnoreCase(uri.getLastPathSegment())) {
      builder.append(path).append("/");
    }
  }
  String baseLinkUrl=!isWiki ? getLinkBaseUrl(baseUrl) : baseUrl;
  return getParsedHtml(source,owner,repoName,!isWiki ? builder.toString() : baseUrl,baseLinkUrl,isWiki);
}
