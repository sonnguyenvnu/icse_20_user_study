private static List<LocalVariableNode> variablesAtIndexes(Set<Integer> indexes,List<Node> arguments){
  List<LocalVariableNode> result=new ArrayList<>();
  for (  Integer i : indexes) {
    if (i < 0) {
      i=arguments.size() + i;
    }
    if (i >= 0 && i < arguments.size()) {
      Node argument=arguments.get(i);
      if (argument instanceof LocalVariableNode) {
        result.add((LocalVariableNode)argument);
      }
    }
  }
  return result;
}
