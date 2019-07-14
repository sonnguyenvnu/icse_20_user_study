public final Object getAttributeValue(Context ctx,Node element,String name){
  Node at=element.getAttributes().getNamedItem(name);
  return at != null ? getValue(ctx,at.getNodeValue()) : null;
}
