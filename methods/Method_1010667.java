public void show(){
  for (  SNode node : myStructuralNodeSets.keySet()) {
    System.err.print("node " + node + " -> ");
    StructuralNodeSet<Integer> superNodes=myStructuralNodeSets.get(node);
    for (    SNode superNode : superNodes) {
    }
    System.err.println();
  }
}
