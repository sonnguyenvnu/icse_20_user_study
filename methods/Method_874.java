private void checkMathRandom(List<ASTMethodDeclaration> methodDeclarations,Object data){
  for (  ASTMethodDeclaration methodDeclaration : methodDeclarations) {
    List<ASTPrimaryPrefix> primaryPrefixes=methodDeclaration.findDescendantsOfType(ASTPrimaryPrefix.class);
    if (primaryPrefixes == null || primaryPrefixes.isEmpty()) {
      continue;
    }
    for (    ASTPrimaryPrefix primaryPrefix : primaryPrefixes) {
      if (primaryPrefix.getType() != Math.class) {
        continue;
      }
      ASTName name=primaryPrefix.getFirstChildOfType(ASTName.class);
      if (name == null || name.getImage() == null || !name.getImage().endsWith(MATH_RANDOM_METHOD)) {
        continue;
      }
      addViolationWithMessage(data,primaryPrefix,MESSAGE_KEY_PREFIX + ".violation.msg.math.random");
    }
  }
}
