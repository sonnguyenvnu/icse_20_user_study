private List<SNode> resolve(Collection<SNodeId> output){
  ArrayList<SNode> rv=new ArrayList<>(output.size());
  for (  SNodeId id : output) {
    SNode node=myCheckpointModel.getNode(id);
    assert node != null : "provided SNodeId comes from getOutput() it's unreasonable to expect model misses the node";
    rv.add(node);
  }
  return rv;
}
