/** 
 * Gets the wrapper for a DOM node, implementing PMD interfaces.
 * @param domNode The node to wrap
 * @return The wrapper
 */
XmlNode wrapDomNode(Node domNode){
  XmlNode wrapper=nodeCache.get(domNode);
  if (wrapper == null) {
    wrapper=new XmlNodeWrapper(this,domNode);
    nodeCache.put(domNode,wrapper);
  }
  return wrapper;
}
