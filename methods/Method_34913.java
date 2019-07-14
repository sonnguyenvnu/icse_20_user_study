private void addToOutgoingUrls(String href,String tag,Attributes attributes){
  curUrl=new ExtractedUrlAnchorPair();
  curUrl.setHref(href);
  curUrl.setTag(tag);
  for (int x=0; x < attributes.getLength(); x++) {
    String attrName=attributes.getLocalName(x);
    String attrVal=attributes.getValue(attrName);
    curUrl.setAttribute(attrName,attrVal);
  }
  outgoingUrls.add(curUrl);
}
