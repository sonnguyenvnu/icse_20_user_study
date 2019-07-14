ResourceValue getResourceInAnnotation(Element element,Class annotationClass,String resourceType,int resourceValue){
  List<ResourceValue> layouts=getResourcesInAnnotation(element,annotationClass,resourceType,Collections.singletonList(resourceValue));
  if (layouts.size() != 1) {
    errorLogger.logError("Expected exactly 1 %s resource in the %s annotation but received %s. Annotated " + "element is %s",resourceType,annotationClass.getSimpleName(),layouts.size(),element.getSimpleName());
    if (layouts.isEmpty()) {
      return new ResourceValue(0);
    }
  }
  return layouts.get(0);
}
