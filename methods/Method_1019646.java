@Override public boolean preprocess(String entryName,InputStream reader,Writer writer,FieldsMetadata fieldsMetadata,IDocumentFormatter formatter,Map<String,Object> sharedContext) throws XDocReportException, IOException {
  try {
    XMLReader xmlReader=XMLReaderFactory.createXMLReader();
    BufferedDocumentContentHandler<?> contentHandler=createBufferedDocumentContentHandler(entryName,fieldsMetadata,formatter,sharedContext);
    xmlReader.setContentHandler(contentHandler);
    xmlReader.parse(new InputSource(reader));
    BufferedDocument document=contentHandler.getBufferedDocument();
    if (document != null) {
      document.save(writer);
      return true;
    }
    return false;
  }
 catch (  SAXException e) {
    throw new XDocReportException(e);
  }
}
