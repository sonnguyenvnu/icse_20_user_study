public static Node getElementNode(Node base,Path path){
  Node el=base;
  int len=path.segments.length;
  for (int i=0; i < len; i++) {
    el=getElementNode(el,path.segments[i]);
    if (el == null) {
      return null;
    }
  }
  return el;
}
