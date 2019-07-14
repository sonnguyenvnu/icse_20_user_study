private Set<WebURL> getOutgoingUrls(String contextURL,HtmlContentHandler contentHandler,String contentCharset) throws UnsupportedEncodingException {
  Set<WebURL> outgoingUrls=new HashSet<>();
  String baseURL=contentHandler.getBaseUrl();
  if (baseURL != null) {
    contextURL=baseURL;
  }
  int urlCount=0;
  for (  ExtractedUrlAnchorPair urlAnchorPair : contentHandler.getOutgoingUrls()) {
    String href=urlAnchorPair.getHref();
    if ((href == null) || href.trim().isEmpty()) {
      continue;
    }
    String hrefLoweredCase=href.trim().toLowerCase();
    if (!hrefLoweredCase.contains("javascript:") && !hrefLoweredCase.contains("mailto:") && !hrefLoweredCase.contains("@")) {
      Charset hrefCharset=((contentCharset == null) || contentCharset.isEmpty()) ? StandardCharsets.UTF_8 : Charset.forName(contentCharset);
      String url=URLCanonicalizer.getCanonicalURL(href,contextURL,hrefCharset);
      if (url != null) {
        WebURL webURL=new WebURL();
        webURL.setTldList(tldList);
        webURL.setURL(url);
        webURL.setTag(urlAnchorPair.getTag());
        webURL.setAnchor(urlAnchorPair.getAnchor());
        webURL.setAttributes(urlAnchorPair.getAttributes());
        outgoingUrls.add(webURL);
        urlCount++;
        if (urlCount > config.getMaxOutgoingLinksToFollow()) {
          break;
        }
      }
    }
  }
  return outgoingUrls;
}
