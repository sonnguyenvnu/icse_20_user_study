private void initLocationInfo(Node node){
  start=node.start;
  end=node.end;
  Node bodyNode=node.parent;
  while (!(bodyNode == null || bodyNode instanceof Function || bodyNode instanceof Class || bodyNode instanceof Module)) {
    bodyNode=bodyNode.parent;
  }
  if ((bodyNode instanceof Function && ((Function)bodyNode).name == node) || (bodyNode instanceof Class && ((Class)bodyNode).name == node) || (bodyNode instanceof Module && ((Module)bodyNode).name == node)) {
    bodyStart=bodyNode.start;
    bodyEnd=bodyNode.end;
  }
 else {
    bodyStart=node.start;
    bodyEnd=node.end;
  }
}
