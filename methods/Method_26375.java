private boolean containerOfSubtyping(Set<String> containerTypeParameters,AnnotationInfo annotation,TypeVariableSymbol typaram,Type tyargument){
  if (!tyargument.hasTag(TypeTag.TYPEVAR)) {
    return false;
  }
  if (!containerTypeParameters.contains(tyargument.asElement().getSimpleName().toString()) || isTypeParameterThreadSafe((TypeVariableSymbol)tyargument.asElement(),containerTypeParameters)) {
    return false;
  }
  if (annotation.containerOf().contains(typaram.getSimpleName().toString())) {
    return false;
  }
  return true;
}
