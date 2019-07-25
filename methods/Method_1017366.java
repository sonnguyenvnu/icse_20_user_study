public synchronized void parser(InputStream file){
  NodeList nodeList=this.parserer(file);
  if (nodeList == null) {
    return;
  }
  for (int i=0; i < nodeList.getLength(); i++) {
    Element node=(Element)nodeList.item(i);
    NodeList arguments=node.getElementsByTagName("argument");
    if (arguments.getLength() == 1) {
      twigPathIndex.addPath(new TwigPath(arguments.item(0).getTextContent()));
    }
 else     if (arguments.getLength() == 2) {
      twigPathIndex.addPath(new TwigPath(arguments.item(0).getTextContent(),arguments.item(1).getTextContent()));
    }
  }
}
