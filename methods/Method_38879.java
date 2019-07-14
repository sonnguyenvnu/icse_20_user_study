/** 
 * Returns <code>true</code> if node contains an attribute.
 */
public boolean hasAttribute(String name){
  if (attributes == null) {
    return false;
  }
  if (!ownerDocument.config.isCaseSensitive()) {
    name=name.toLowerCase();
  }
  for (int i=0, attributesSize=attributes.size(); i < attributesSize; i++) {
    Attribute attr=attributes.get(i);
    if (attr.getName().equals(name)) {
      return true;
    }
  }
  return false;
}
