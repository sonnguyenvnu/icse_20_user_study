private void addDeclarations(SortedMap<Integer,Node> map,List<? extends Node> nodes){
  for (  Node node : nodes) {
    map.put((node.getBeginLine() << 16) + node.getBeginColumn(),node);
  }
}
