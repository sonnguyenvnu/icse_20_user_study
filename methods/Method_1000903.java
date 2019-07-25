public static AnnotationMap merge(AnnotationMap primary,AnnotationMap secondary){
  if (primary == null || primary._annotations == null || primary._annotations.isEmpty()) {
    return secondary;
  }
  if (secondary == null || secondary._annotations == null || secondary._annotations.isEmpty()) {
    return primary;
  }
  HashMap<Class<?>,Annotation> annotations=new HashMap<Class<?>,Annotation>();
  for (  Annotation ann : secondary._annotations.values()) {
    annotations.put(ann.annotationType(),ann);
  }
  for (  Annotation ann : primary._annotations.values()) {
    annotations.put(ann.annotationType(),ann);
  }
  return new AnnotationMap(annotations);
}
