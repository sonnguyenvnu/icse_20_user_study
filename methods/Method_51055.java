@Override public Object getDocumentNode(Object contextNode){
  if (isDocument(contextNode)) {
    return contextNode;
  }
  if (null == contextNode) {
    throw new RuntimeException("contextNode may not be null");
  }
  return getDocumentNode(getParentNode(contextNode));
}
