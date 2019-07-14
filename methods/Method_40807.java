public static List<Value> interpList(List<Node> nodes,Scope s){
  List<Value> values=new ArrayList<>();
  for (  Node n : nodes) {
    values.add(n.interp(s));
  }
  return values;
}
