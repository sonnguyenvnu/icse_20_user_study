private FieldImmutabilityType initializedInConstructor(List<NameOccurrence> usages,Set<ASTConstructorDeclaration> allConstructors){
  FieldImmutabilityType result=FieldImmutabilityType.MUTABLE;
  int methodInitCount=0;
  int lambdaUsage=0;
  Set<ASTConstructorDeclaration> consSet=new HashSet<>();
  for (  NameOccurrence occ : usages) {
    JavaNameOccurrence jocc=(JavaNameOccurrence)occ;
    if (jocc.isOnLeftHandSide() || jocc.isSelfAssignment()) {
      Node node=jocc.getLocation();
      ASTConstructorDeclaration constructor=node.getFirstParentOfType(ASTConstructorDeclaration.class);
      if (constructor != null) {
        if (inLoopOrTry(node)) {
          continue;
        }
        if (node.getFirstParentOfType(ASTIfStatement.class) != null) {
          methodInitCount++;
        }
        if (inAnonymousInnerClass(node)) {
          methodInitCount++;
        }
 else         if (node.getFirstParentOfType(ASTLambdaExpression.class) != null) {
          lambdaUsage++;
        }
 else {
          consSet.add(constructor);
        }
      }
 else {
        if (node.getFirstParentOfType(ASTMethodDeclaration.class) != null) {
          methodInitCount++;
        }
 else         if (node.getFirstParentOfType(ASTLambdaExpression.class) != null) {
          lambdaUsage++;
        }
      }
    }
  }
  if (usages.isEmpty() || methodInitCount == 0 && lambdaUsage == 0 && consSet.isEmpty()) {
    result=FieldImmutabilityType.CHECKDECL;
  }
 else {
    allConstructors.removeAll(consSet);
    if (allConstructors.isEmpty() && methodInitCount == 0 && lambdaUsage == 0) {
      result=FieldImmutabilityType.IMMUTABLE;
    }
  }
  return result;
}
