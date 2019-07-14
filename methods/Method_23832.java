public XML addChild(XML child){
  Document document=node.getOwnerDocument();
  Node newChild=document.importNode((Node)child.getNative(),true);
  return appendChild(newChild);
}
