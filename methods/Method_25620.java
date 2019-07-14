private AnnotationTree findAnnotation(MethodTree methodTree,VisitorState state,String annotationName){
  AnnotationTree annotationNode=null;
  for (  AnnotationTree annotation : methodTree.getModifiers().getAnnotations()) {
    if (ASTHelpers.getSymbol(annotation).equals(state.getSymbolFromString(annotationName))) {
      annotationNode=annotation;
    }
  }
  return annotationNode;
}
