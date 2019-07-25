/** 
 * Loads the given attribute in the current sequence.
 * @param attr The W3C DOM attribute node to load.
 */
private void load(Attr attr){
  handlePrefixMapping(attr.getNamespaceURI(),attr.getPrefix());
  load(this.efactory.makeAttribute(attr.getNamespaceURI(),attr.getLocalName(),attr.getNodeName(),attr.getValue()));
}
