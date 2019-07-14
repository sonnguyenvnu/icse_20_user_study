/** 
 * Attempt to create a dynamic context on which to evaluate the  {@link #xpathExpression}.
 * @param elementNode the node on which to create the context; generally this node is the root node of the SaxonTree
 * @return the dynamic context on which to run the query
 * @throws XPathException if the supplied value does not conform to the required type of thevariable, when setting up the dynamic context; or if the supplied value contains a node that does not belong to this Configuration (or another Configuration that shares the same namePool)
 */
private XPathDynamicContext createDynamicContext(final ElementNode elementNode) throws XPathException {
  final XPathDynamicContext dynamicContext=xpathExpression.createDynamicContext(elementNode);
  for (  final XPathVariable xpathVariable : xpathVariables) {
    final String variableName=xpathVariable.getVariableQName().getLocalName();
    for (    final Map.Entry<PropertyDescriptor<?>,Object> entry : super.properties.entrySet()) {
      if (variableName.equals(entry.getKey().name())) {
        final ValueRepresentation valueRepresentation=getRepresentation(entry.getKey(),entry.getValue());
        dynamicContext.setVariable(xpathVariable,valueRepresentation);
      }
    }
  }
  return dynamicContext;
}
