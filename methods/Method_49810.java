@Override public Element createElement(String tagName) throws DOMException {
  tagName=tagName.toLowerCase();
  if (tagName.equals("text") || tagName.equals("img") || tagName.equals("video")) {
    return new SmilRegionMediaElementImpl(this,tagName);
  }
 else   if (tagName.equals("audio")) {
    return new SmilMediaElementImpl(this,tagName);
  }
 else   if (tagName.equals("layout")) {
    return new SmilLayoutElementImpl(this,tagName);
  }
 else   if (tagName.equals("root-layout")) {
    return new SmilRootLayoutElementImpl(this,tagName);
  }
 else   if (tagName.equals("region")) {
    return new SmilRegionElementImpl(this,tagName);
  }
 else   if (tagName.equals("ref")) {
    return new SmilRefElementImpl(this,tagName);
  }
 else   if (tagName.equals("par")) {
    return new SmilParElementImpl(this,tagName);
  }
 else   if (tagName.equals("vcard")) {
    return new SmilRegionMediaElementImpl(this,tagName);
  }
 else {
    return new SmilElementImpl(this,tagName);
  }
}
