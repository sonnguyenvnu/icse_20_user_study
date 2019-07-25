/** 
 * {@inheritDoc}
 */
@Override public void parse(String systemId) throws IOException, SAXException {
  throw new SAXException(this.getClass().getName() + " cannot be used with system identifiers (URIs)");
}
