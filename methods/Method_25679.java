private boolean classOrEnclosingClassIsForbiddenByAnnotation(Symbol clazz,VisitorState state){
  if (!alsoForbidApisAnnotated.isPresent()) {
    return false;
  }
  for (; clazz instanceof ClassSymbol; clazz=clazz.owner) {
    if (hasAnnotationForbiddingUse(clazz,state)) {
      return true;
    }
  }
  return false;
}
