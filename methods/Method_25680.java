private boolean hasAnnotationForbiddingUse(Symbol sym,VisitorState state){
  return alsoForbidApisAnnotated.isPresent() && ASTHelpers.hasAnnotation(sym,alsoForbidApisAnnotated.get(),state);
}
