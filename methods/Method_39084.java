protected Class<? extends MadvocScope> resolveScopeClassFromAnnotations(final Annotation[] annotations){
  Class<? extends MadvocScope> scope=null;
  for (  final Annotation annotation : annotations) {
    if (annotation instanceof Scope) {
      if (scope != null) {
        throw new MadvocException("Scope already defined: " + scope);
      }
      scope=((Scope)annotation).value();
    }
 else {
      Class<? extends MadvocScope> annotationScope=null;
      final Annotation[] annotationAnnotations=annotation.annotationType().getAnnotations();
      for (      final Annotation innerAnnotation : annotationAnnotations) {
        if (innerAnnotation instanceof Scope) {
          annotationScope=((Scope)innerAnnotation).value();
        }
      }
      if (annotationScope != null) {
        if (scope == null) {
          scope=annotationScope;
        }
 else {
          throw new MadvocException("Scope already defined: " + scope);
        }
      }
    }
  }
  return scope;
}
