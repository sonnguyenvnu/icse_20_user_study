@Override public ASTAnnotation getAnnotation(String annotQualifiedName){
  List<ASTAnnotation> annotations=getDeclaredAnnotations();
  for (  ASTAnnotation annotation : annotations) {
    ASTName name=annotation.getFirstDescendantOfType(ASTName.class);
    if (name != null && TypeHelper.isA(name,annotQualifiedName)) {
      return annotation;
    }
  }
  return null;
}
