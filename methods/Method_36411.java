public static void visitNodes(Context ctx,XAnnotatedList xam,Element base,Path path,NodeVisitor visitor,Collection<Object> result){
  Node el=base;
  int len=path.segments.length - 1;
  for (int i=0; i < len; i++) {
    el=getElementNode(el,path.segments[i]);
    if (el == null) {
      return;
    }
  }
  String name=path.segments[len];
  if (path.attribute != null) {
    visitAttributes(ctx,xam,el,name,path.attribute,visitor,result);
  }
 else {
    visitElements(ctx,xam,el,name,visitor,result);
  }
}
