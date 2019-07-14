static protected List<Type> resolveAndConstructList(List<? extends Node> nodes,Scope s,int tag) throws Exception {
  if (nodes == null) {
    return null;
  }
 else {
    List<Type> typeList=new ArrayList<Type>();
    for (    Node n : nodes) {
      typeList.add(resolveExpr(n,s,tag));
    }
    return typeList;
  }
}
