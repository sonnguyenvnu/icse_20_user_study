public static void visitMapElements(Context ctx,XAnnotatedMap xam,Node base,String name,NodeMapVisitor visitor,Map<String,Object> result){
  Node p=base.getFirstChild();
  while (p != null) {
    if (p.getNodeType() == Node.ELEMENT_NODE) {
      if (name.equals(p.getNodeName())) {
        String key=getNodeValue((Element)p,xam.key);
        if (key != null) {
          visitor.visitNode(ctx,xam,p,key,result);
        }
      }
    }
    p=p.getNextSibling();
  }
}
