@NonNull public Element fromTransformDelegateMethod(@NonNull ExecutableElement method,Element transform){
  if (transform instanceof TransformedField.Transformable) {
    return new TransformedField(method.getSimpleName().toString(),TypeName.get(method.getReturnType()),(TransformedField.Transformable)transform);
  }
  return transform;
}
