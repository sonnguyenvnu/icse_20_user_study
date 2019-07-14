public List<String> getSparklrPhotoIds() throws SparklrException {
  try {
    InputStream photosXML=new ByteArrayInputStream(getSparklrRestTemplate().getForObject(URI.create(getSparklrPhotoListURL()),byte[].class));
    final List<String> photoIds=new ArrayList<String>();
    SAXParserFactory parserFactory=SAXParserFactory.newInstance();
    parserFactory.setValidating(false);
    parserFactory.setXIncludeAware(false);
    parserFactory.setNamespaceAware(false);
    SAXParser parser=parserFactory.newSAXParser();
    parser.parse(photosXML,new DefaultHandler(){
      @Override public void startElement(      String uri,      String localName,      String qName,      Attributes attributes) throws SAXException {
        if ("photo".equals(qName)) {
          photoIds.add(attributes.getValue("id"));
        }
      }
    }
);
    return photoIds;
  }
 catch (  IOException e) {
    throw new IllegalStateException(e);
  }
catch (  SAXException e) {
    throw new IllegalStateException(e);
  }
catch (  ParserConfigurationException e) {
    throw new IllegalStateException(e);
  }
}
