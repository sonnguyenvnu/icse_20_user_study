public String getString(String name,String defaultValue){
  NamedNodeMap attrs=node.getAttributes();
  if (attrs != null) {
    Node attr=attrs.getNamedItem(name);
    if (attr != null) {
      return attr.getNodeValue();
    }
  }
  return defaultValue;
}
