public void save(ContentHandler contentHandler) throws SAXException {
  List<CSSStyle> cssStyles=getCSSStyles();
  if (ignoreStylesIfUnused && cssStyles.size() < 1) {
    return;
  }
  SAXHelper.startElement(contentHandler,XHTMLConstants.STYLE_ELEMENT,null);
  for (  CSSStyle style : cssStyles) {
    if (indent != null) {
      SAXHelper.characters(contentHandler,CR);
    }
    style.save(contentHandler);
  }
  SAXHelper.endElement(contentHandler,XHTMLConstants.STYLE_ELEMENT);
}
