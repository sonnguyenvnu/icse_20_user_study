@Override public Boolean visit(ScopeBinding command){
  Scope scope=command.getScope();
  Class<? extends Annotation> annotationType=command.getAnnotationType();
  if (!Annotations.isScopeAnnotation(annotationType)) {
    errors.withSource(annotationType).missingScopeAnnotation();
  }
  if (!Annotations.isRetainedAtRuntime(annotationType)) {
    errors.withSource(annotationType).missingRuntimeRetention(command.getSource());
  }
  Scope existing=injector.state.getScope(Objects.requireNonNull(annotationType,"annotation type"));
  if (existing != null) {
    errors.duplicateScopes(existing,annotationType,scope);
  }
 else {
    injector.state.putAnnotation(annotationType,Objects.requireNonNull(scope,"scope"));
  }
  return true;
}
