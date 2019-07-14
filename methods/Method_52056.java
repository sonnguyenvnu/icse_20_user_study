private boolean isJUnit5Class(ASTCompilationUnit node){
  return doesNodeContainJUnitAnnotation(node,JUNIT5_CLASS_NAME);
}
