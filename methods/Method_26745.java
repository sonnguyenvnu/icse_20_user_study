@SuppressWarnings("unchecked") public static ImmutableClassToInstanceMap<Annotation> annotationMap(Symbol symbol){
  ImmutableClassToInstanceMap.Builder<Annotation> builder=ImmutableClassToInstanceMap.builder();
  for (  Compound compound : symbol.getAnnotationMirrors()) {
    Name qualifiedAnnotationType=((TypeElement)compound.getAnnotationType().asElement()).getQualifiedName();
    try {
      Class<? extends Annotation> annotationClazz=Class.forName(qualifiedAnnotationType.toString()).asSubclass(Annotation.class);
      builder.put((Class)annotationClazz,AnnotationProxyMaker.generateAnnotation(compound,annotationClazz));
    }
 catch (    ClassNotFoundException e) {
      throw new IllegalArgumentException("Unrecognized annotation type",e);
    }
  }
  return builder.build();
}
