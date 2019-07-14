private boolean isJUnit5Method(ASTMethodDeclaration method){
  return isJUnit5Class && doesNodeContainJUnitAnnotation(method.jjtGetParent(),JUNIT5_CLASS_NAME);
}
