private Description tryToReplaceAnnotation(MethodTree methodTree,VisitorState state,String badAnnotation,String goodAnnotation){
  String finalName=getUnqualifiedClassName(goodAnnotation);
  if (hasAnnotation(badAnnotation).matches(methodTree,state)) {
    AnnotationTree annotationTree=findAnnotation(methodTree,state,badAnnotation);
    return describeMatch(annotationTree,SuggestedFix.builder().addImport(goodAnnotation).replace(annotationTree,"@" + finalName).build());
  }
 else {
    return null;
  }
}
