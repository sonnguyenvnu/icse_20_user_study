public String getContent(String defaultValue){
  String s=node.getTextContent();
  return (s != null) ? s : defaultValue;
}
