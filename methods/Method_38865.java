/** 
 * Renders node name.
 */
protected String resolveNodeName(final Node node){
switch (tagCase) {
case DEFAULT:
    return node.getNodeName();
case RAW:
  return node.getNodeRawName();
case LOWERCASE:
return node.getNodeRawName().toLowerCase();
case UPPERCASE:
return node.getNodeRawName().toUpperCase();
}
return null;
}
