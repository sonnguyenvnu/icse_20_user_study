void save(Updater updater){
  Set<ReflectiveHint> explicitReflectiveHintsForNode=ReflectiveHint.getExplicitReflectiveHintsForNode(updater,myNode);
  explicitReflectiveHintsForNode.forEach(reflectiveHint -> reflectiveHint.revoke(updater,myNode));
  myHints.forEach(reflectiveHint -> reflectiveHint.apply(updater,myNode));
}
