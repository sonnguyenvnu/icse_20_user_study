public void save(ContentHandler contentHandler) throws SAXException {
  StringBuilder style=new StringBuilder(tagName);
  if (className != null) {
    style.append('.');
    style.append(className);
  }
  style.append('{');
  buildInlineStyles(style);
  style.append('}');
  SAXHelper.characters(contentHandler,style.toString());
}
