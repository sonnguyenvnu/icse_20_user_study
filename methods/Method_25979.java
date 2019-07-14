private Description describe(ClassTree classTree,VisitorState state,@Nullable Retention retention){
  if (retention == null) {
    return describeMatch(classTree,SuggestedFix.builder().addImport("java.lang.annotation.Retention").addStaticImport("java.lang.annotation.RetentionPolicy.RUNTIME").prefixWith(classTree,"@Retention(RUNTIME)\n").build());
  }
  AnnotationTree retentionNode=null;
  for (  AnnotationTree annotation : classTree.getModifiers().getAnnotations()) {
    if (ASTHelpers.getSymbol(annotation).equals(state.getSymbolFromString(RETENTION_ANNOTATION))) {
      retentionNode=annotation;
    }
  }
  return describeMatch(retentionNode,SuggestedFix.builder().addImport("java.lang.annotation.Retention").addStaticImport("java.lang.annotation.RetentionPolicy.RUNTIME").replace(retentionNode,"@Retention(RUNTIME)").build());
}
