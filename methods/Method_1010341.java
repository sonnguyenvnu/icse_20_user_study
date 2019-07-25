public static List<SNode> copy(List<SNode> nodes,Map<SNode,SNode> mapping){
  List<SNode> result=clone(nodes,mapping);
  for (  SNode node : nodes) {
    addReferences(node,mapping,false);
  }
  return result;
}
