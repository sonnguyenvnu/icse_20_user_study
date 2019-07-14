private void handleAnnotationMethods(){
  for (  ExecutableElement method : ElementFilter.methodsIn(baseAnnotation.getEnclosedElements())) {
    elements.add(MoreElements.isAnnotationPresent(method,StringRes.class) ? modelFactory.fromStringResourceAnnotationMethod(method) : modelFactory.fromAnnotationMethod(method));
  }
}
