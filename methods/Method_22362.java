private void handleBaseBuilderMethods(){
  for (  ExecutableElement method : ElementFilter.methodsIn(baseBuilder.getEnclosedElements())) {
    if (method.getAnnotation(PreBuild.class) != null) {
      elements.add(modelFactory.fromPreBuildDelegateMethod(method));
    }
 else     if (method.getAnnotation(Transform.class) != null) {
      final String transform=method.getAnnotation(Transform.class).methodName();
      elements.stream().filter(field -> field.getName().equals(transform)).findAny().ifPresent(element -> elements.set(elements.indexOf(element),modelFactory.fromTransformDelegateMethod(method,element)));
    }
 else     if (method.getAnnotation(ConfigurationValue.class) != null) {
      elements.add(modelFactory.fromConfigDelegateMethod(method));
    }
 else     if (method.getAnnotation(BuilderMethod.class) != null) {
      elements.add(modelFactory.fromBuilderDelegateMethod(method));
    }
  }
}
