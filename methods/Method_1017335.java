public void parser(InputStream file){
  NodeList nodeList=this.parserer(file);
  if (nodeList == null) {
    return;
  }
  for (int i=0; i < nodeList.getLength(); i++) {
    Element node=(Element)nodeList.item(i);
    this.formTypeMap.getMap().put(node.getTextContent(),node.getAttribute("key"));
  }
}
