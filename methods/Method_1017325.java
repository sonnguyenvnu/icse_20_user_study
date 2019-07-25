public void parser(InputStream file){
  NodeList nodeList=this.parserer(file);
  if (nodeList == null) {
    return;
  }
  for (int i=0; i < nodeList.getLength(); i++) {
    Element node=(Element)nodeList.item(i);
    this.list.put(node.getAttribute("event"),node.getAttribute("name"));
    if (((Element)node.getParentNode()).hasAttribute("class")) {
      this.events.add(new EventDispatcherSubscribedEvent(node.getAttribute("event"),((Element)node.getParentNode()).getAttribute("class"),null).setType(node.getAttribute("name")));
    }
  }
}
