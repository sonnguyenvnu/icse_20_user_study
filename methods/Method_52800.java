/** 
 * Adds a VariableAccess to a dataflow node.
 * @param node location of the access of a variable
 * @param va variable access to add
 * @param flow dataflownodes that can contain the node.
 */
private void addVariableAccess(Node node,VariableAccess va,List<DataFlowNode> flow){
  for (int i=flow.size() - 1; i > 0; i--) {
    DataFlowNode inode=flow.get(i);
    if (inode.getNode() == null) {
      continue;
    }
    List<? extends Node> children=inode.getNode().findDescendantsOfType(node.getClass());
    for (    Node n : children) {
      if (node.equals(n)) {
        List<VariableAccess> v=new ArrayList<>();
        v.add(va);
        inode.setVariableAccess(v);
        return;
      }
    }
  }
}
