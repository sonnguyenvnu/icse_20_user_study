@Override public Rendering render(AnchorLinkNode node){
  String name=node.getName();
  return new Rendering('#' + name,node.getText()).withAttribute("name",name).withAttribute("id",name).withAttribute("class","anchor");
}
