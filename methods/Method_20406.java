ResourceValue getLayoutInAnnotation(Element element,Class annotationClass){
  List<ResourceValue> layouts=getLayoutsInAnnotation(element,annotationClass);
  if (layouts.size() != 1) {
    errorLogger.logError("Expected exactly 1 layout resource in the %s annotation but received %s. Annotated " + "element is %s",annotationClass.getSimpleName(),layouts.size(),element.getSimpleName());
    if (layouts.isEmpty()) {
      return new ResourceValue(0);
    }
  }
  return layouts.get(0);
}
