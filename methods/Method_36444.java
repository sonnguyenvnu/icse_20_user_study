protected static Annotation checkMemberAnnotation(AnnotatedElement ae){
  Annotation[] annos=ae.getAnnotations();
  for (  Annotation anno : annos) {
    if (anno.annotationType().isAnnotationPresent(XMemberAnnotation.class)) {
      return anno;
    }
  }
  return null;
}
