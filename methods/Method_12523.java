private static URI rewriteUrl(UriComponents oldUrl,String targetUrl){
  String[] newPathSegments=oldUrl.getPathSegments().subList(1,oldUrl.getPathSegments().size()).toArray(new String[]{});
  return UriComponentsBuilder.fromUriString(targetUrl).pathSegment(newPathSegments).query(oldUrl.getQuery()).build(true).toUri();
}
