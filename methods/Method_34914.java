@Override public void characters(char[] ch,int start,int length) throws SAXException {
  if (isWithinBodyElement) {
    if (bodyText.length() > 0) {
      bodyText.append(' ');
    }
    bodyText.append(ch,start,length);
    if (anchorFlag) {
      anchorText.append(new String(ch,start,length));
    }
  }
}
