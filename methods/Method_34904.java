/** 
 * Returns a transformer handler that serializes incoming SAX events to XHTML or HTML (depending the given method) using the given output encoding.
 * @param encoding output encoding, or <code>null</code> for the platform default
 */
private static TransformerHandler getTransformerHandler(OutputStream out,String method,String encoding) throws TransformerConfigurationException {
  TransformerHandler transformerHandler=SAX_TRANSFORMER_FACTORY.newTransformerHandler();
  Transformer transformer=transformerHandler.getTransformer();
  transformer.setOutputProperty(OutputKeys.METHOD,method);
  transformer.setOutputProperty(OutputKeys.INDENT,"yes");
  if (encoding != null) {
    transformer.setOutputProperty(OutputKeys.ENCODING,encoding);
  }
  transformerHandler.setResult(new StreamResult(new PrintStream(out)));
  return transformerHandler;
}
