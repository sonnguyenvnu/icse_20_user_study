private Set<WebURL> parseOutgoingUrls(WebURL referringPage) throws UnsupportedEncodingException {
  Set<String> extractedUrls=extractUrlInCssText(this.getTextContent());
  final String pagePath=referringPage.getPath();
  final String pageUrl=referringPage.getURL();
  Set<WebURL> outgoingUrls=new HashSet<>();
  for (  String url : extractedUrls) {
    String relative=getLinkRelativeTo(pagePath,url);
    String absolute=getAbsoluteUrlFrom(URLCanonicalizer.getCanonicalURL(pageUrl),relative);
    WebURL webURL=new WebURL();
    webURL.setURL(absolute);
    outgoingUrls.add(webURL);
  }
  return outgoingUrls;
}
