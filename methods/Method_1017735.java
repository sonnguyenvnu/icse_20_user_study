public PropertyParameterContext annotate(AnnotatedElement element){
  When quantifier=element.getAnnotation(When.class);
  addQuantifier(quantifier);
  addConstraint(quantifier);
  typeContext.annotate(element);
  return this;
}
