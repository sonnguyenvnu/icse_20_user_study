private List<Node> initializedInConstructor(List<NameOccurrence> usages){
  final List<Node> unsafeAssignments=new ArrayList<>();
  for (  NameOccurrence occ : usages) {
    if (((JavaNameOccurrence)occ).isOnLeftHandSide()) {
      Node node=occ.getLocation();
      Node constructor=node.getFirstParentOfType(ASTConstructorDeclaration.class);
      if (constructor != null) {
        unsafeAssignments.add(node);
      }
    }
  }
  return unsafeAssignments;
}
