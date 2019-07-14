static boolean isTestMethodOrClass(final ApexNode<?> node){
  final List<ASTModifierNode> modifierNode=node.findChildrenOfType(ASTModifierNode.class);
  for (  final ASTModifierNode m : modifierNode) {
    if (m.isTest()) {
      return true;
    }
  }
  final String className=node.getNode().getDefiningType().getApexName();
  return className.endsWith("Test");
}
