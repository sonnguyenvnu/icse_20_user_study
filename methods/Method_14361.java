static protected String composeName(String prefix,String localName){
  return prefix != null && prefix.length() > 0 ? (prefix + ":" + localName) : localName;
}
