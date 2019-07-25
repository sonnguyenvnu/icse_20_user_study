public void parser(InputStream file){
  NodeList nodeList=this.parserer(file);
  if (nodeList == null) {
    return;
  }
  for (int i=0; i < nodeList.getLength(); i++) {
    Element node=(Element)nodeList.item(i);
    NodeList arguments=node.getElementsByTagName("argument");
    if (arguments.getLength() == 4) {
      this.domainFileMaps.add(new DomainFileMap(arguments.item(0).getTextContent(),arguments.item(1).getTextContent(),arguments.item(2).getTextContent(),arguments.item(3).getTextContent()));
    }
  }
}
