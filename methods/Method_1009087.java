/** 
 * Runs the recorder on the specified input source.
 * @param is The input source.
 * @return The recorded sequence of events.
 * @throws LoadingException If thrown whilst parsing.
 * @throws IOException      Should I/O error occur.
 */
public EventSequence process(InputSource is) throws LoadingException, IOException {
  if (reader == null || newReader) {
    init();
  }
  reader.setContentHandler(new RecorderHandler());
  reader.setErrorHandler(new RecorderErrorHandler());
  try {
    reader.setFeature("http://xml.org/sax/features/namespaces",this.config.isNamespaceAware());
    reader.setFeature("http://xml.org/sax/features/namespace-prefixes",this.config.isReportPrefixDifferences());
    reader.parse(is);
  }
 catch (  SAXException ex) {
    throw new LoadingException(ex);
  }
  return this.sequence;
}
