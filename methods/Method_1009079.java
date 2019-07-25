/** 
 * Runs the recorder on the specified input source.
 * @param is The input source.
 * @return The recorded sequence of events.
 * @throws LoadingException If thrown while parsing.
 */
public EventSequence process(InputSource is) throws LoadingException {
  this.isFragment=false;
  try {
    DocumentBuilder builder=XmlUtils.getNewDocumentBuilder();
    Document document=builder.parse(is);
    return this.process(document);
  }
 catch (  Exception ex) {
    throw new LoadingException(ex);
  }
}
