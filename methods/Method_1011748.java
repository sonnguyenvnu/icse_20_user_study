@Override public void apply(@NotNull SModel model,@NotNull NodeCopier nodeCopier){
  SNode node=model.getNode(getAffectedNodeId());
  assert node != null;
  node.setProperty(myProperty,myNewValue);
}
