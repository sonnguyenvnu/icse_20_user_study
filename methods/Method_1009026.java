/** 
 * @inheritDoc 
 */
@Override public void warning(SAXParseException ex) throws SAXException {
  if (IgnoreAllErrorHandler.warnOnExceptions) {
    log.warn("",ex);
  }
  if (IgnoreAllErrorHandler.throwExceptions) {
    throw ex;
  }
}
