protected static XmlConfigurator parse(InputStream stream,boolean validate) throws java.io.IOException {
  try {
    DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    factory.setValidating(validate);
    factory.setNamespaceAware(validate);
    if (validate)     factory.setAttribute(JAXP_SCHEMA_LANGUAGE,W3C_XML_SCHEMA);
    DocumentBuilder builder=factory.newDocumentBuilder();
    builder.setEntityResolver((publicId,systemId) -> {
      if (systemId != null && systemId.startsWith("http://www.jgroups.org/schema/JGroups-")) {
        String schemaName=systemId.substring("http://www.jgroups.org/".length());
        InputStream schemaIs=getAsInputStreamFromClassLoader(schemaName);
        if (schemaIs == null) {
          throw new IOException("Schema not found from classloader: " + schemaName);
        }
        InputSource source=new InputSource(schemaIs);
        source.setPublicId(publicId);
        source.setSystemId(systemId);
        return source;
      }
      return null;
    }
);
    final AtomicReference<SAXParseException> exceptionRef=new AtomicReference<>();
    builder.setErrorHandler(new ErrorHandler(){
      public void warning(      SAXParseException exception) throws SAXException {
        log.warn(Util.getMessage("ParseFailure"),exception);
      }
      public void fatalError(      SAXParseException exception) throws SAXException {
        exceptionRef.set(exception);
      }
      public void error(      SAXParseException exception) throws SAXException {
        exceptionRef.set(exception);
      }
    }
);
    Document document=builder.parse(stream);
    if (exceptionRef.get() != null) {
      throw exceptionRef.get();
    }
    Element configElement=document.getDocumentElement();
    return parse(configElement);
  }
 catch (  Exception x) {
    throw new IOException(String.format(Util.getMessage("ParseError"),x.getLocalizedMessage()));
  }
}
