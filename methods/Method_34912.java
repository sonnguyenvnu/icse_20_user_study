private void addToOutgoingUrls(String href,String tag){
  curUrl=new ExtractedUrlAnchorPair();
  curUrl.setHref(href);
  curUrl.setTag(tag);
  outgoingUrls.add(curUrl);
}
