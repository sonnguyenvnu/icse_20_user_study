private String getDoubanDesktopSiteUrl(String url){
  Uri uri=Uri.parse(url);
  if (!TextUtils.equals(uri.getHost(),"m.douban.com") || TextUtils.equals(CollectionUtils.firstOrNull(uri.getPathSegments()),"page")) {
    return url;
  }
  return uri.buildUpon().path("/to_pc/").appendQueryParameter("url",url).build().toString();
}
