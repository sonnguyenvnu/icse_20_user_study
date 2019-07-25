public void substitute(SNode oldVar,SNode type){
  for (  ConditionKind conditionKind : new THashSet<>(myBlocksAndInputs.keySet())) {
    ManyToManyMap<SNode,Block> map=myBlocksAndInputs.get(conditionKind);
    Set<Block> blocks=map.getByFirst(oldVar);
    if (blocks == null) {
      return;
    }
    List<SNode> unresolvedInputs=conditionKind.getUnresolvedInputs(type,this);
    for (    Block block : new THashSet<>(blocks)) {
      for (      SNode variable : unresolvedInputs) {
        addInputAndTrack(block,variable,conditionKind);
      }
      removeInputAndTrack(block,oldVar,conditionKind);
      testInputsResolved(block);
    }
  }
}
