@Override public Site getSite(){
  if (site == null) {
    site=Site.me().setDomain("progressdaily.diandian.com").setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
  }
  return site;
}
