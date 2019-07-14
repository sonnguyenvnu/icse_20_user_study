private static List<Annotation> getLibraryAnnotations(VariableElement param,List<Class<? extends Annotation>> permittedAnnotations){
  List<Annotation> paramAnnotations=new ArrayList<>();
  for (  Class<? extends Annotation> possibleMethodParamAnnotation : permittedAnnotations) {
    final Annotation paramAnnotation=param.getAnnotation(possibleMethodParamAnnotation);
    if (paramAnnotation != null) {
      paramAnnotations.add(paramAnnotation);
    }
  }
  return paramAnnotations;
}
