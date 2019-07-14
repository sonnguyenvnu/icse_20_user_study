protected static XObject checkObjectAnnotation(AnnotatedElement ae,ClassLoader classLoader){
  return ae.getAnnotation(XObject.class);
}
