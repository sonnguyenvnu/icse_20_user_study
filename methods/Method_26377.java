/** 
 * Returns true if the given type parameter's declaration is annotated with  {@link #typeParameterAnnotation} indicated it will only ever be instantiated with thread-safe types.
 */
public boolean hasThreadSafeTypeParameterAnnotation(TypeVariableSymbol symbol){
  return typeParameterAnnotation != null && symbol.getAnnotationMirrors().stream().anyMatch(t -> t.type.tsym.flatName().contentEquals(typeParameterAnnotation.getName()));
}
