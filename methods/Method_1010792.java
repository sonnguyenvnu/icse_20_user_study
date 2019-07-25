static ReflectiveUpdaterHintsState load(Updater updater,SNode node){
  Set<ReflectiveHint> explicitReflectiveHintsForNode=ReflectiveHint.getExplicitReflectiveHintsForNode(updater,node);
  return new ReflectiveUpdaterHintsState(node,explicitReflectiveHintsForNode);
}
