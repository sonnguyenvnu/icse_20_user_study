public void parser(InputStream file){
  NodeList nodeList=this.parserer(file);
  if (nodeList == null) {
    return;
  }
  for (int i=0; i < nodeList.getLength(); i++) {
    Element node=(Element)nodeList.item(i);
    NodeList arguments=node.getElementsByTagName("argument");
    if (arguments.getLength() == 2) {
      String globalName=arguments.item(0).getTextContent();
      Element nodeValue=(Element)arguments.item(1);
      if (nodeValue.hasAttribute("id")) {
        this.twigGlobals.put(globalName,new TwigGlobalVariable(globalName,nodeValue.getAttribute("id"),TwigGlobalEnum.SERVICE));
      }
 else {
        this.twigGlobals.put(globalName,new TwigGlobalVariable(globalName,arguments.item(1).getTextContent(),TwigGlobalEnum.TEXT));
      }
    }
  }
}
