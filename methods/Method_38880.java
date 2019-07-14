protected int indexOfAttributeInstance(String name){
  if (attributes == null) {
    return -1;
  }
  if (!ownerDocument.config.isCaseSensitive()) {
    name=name.toLowerCase();
  }
  for (int i=0, attributesSize=attributes.size(); i < attributesSize; i++) {
    Attribute attr=attributes.get(i);
    if (attr.getName().equals(name)) {
      return i;
    }
  }
  return -1;
}
