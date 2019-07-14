public static List<Value> typecheckList(List<Node> nodes,Scope s){
  List<Value> types=new ArrayList<>();
  for (  Node n : nodes) {
    types.add(n.typecheck(s));
  }
  return types;
}
