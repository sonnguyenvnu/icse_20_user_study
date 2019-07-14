/** 
 * Renders attribute name.
 */
protected String resolveAttributeName(final Node node,final Attribute attribute){
switch (attributeCase) {
case DEFAULT:
    return attribute.getName();
case RAW:
  return attribute.getRawName();
case LOWERCASE:
return attribute.getRawName().toLowerCase();
case UPPERCASE:
return attribute.getRawName().toUpperCase();
}
return null;
}
