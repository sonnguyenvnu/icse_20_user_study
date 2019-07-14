static Class<? extends Annotation> getAnnotationClass(ClassName name){
  try {
    return (Class<? extends Annotation>)getClass(name);
  }
 catch (  ClassCastException e) {
    return null;
  }
}
