private TypeMirror boxed(TypeMirror possiblePrimitive){
  if (possiblePrimitive.getKind().isPrimitive()) {
    return typeUtils.boxedClass((PrimitiveType)possiblePrimitive).asType();
  }
 else {
    return possiblePrimitive;
  }
}
