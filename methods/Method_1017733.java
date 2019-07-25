public ParameterTypeContext annotate(AnnotatedElement element){
  this.annotatedElement=element;
  List<From> generators=allAnnotationsByType(element,From.class);
  if (!generators.isEmpty() && element instanceof AnnotatedWildcardType)   throw new IllegalArgumentException("Wildcards cannot be marked with @From");
  addGenerators(generators);
  return this;
}
