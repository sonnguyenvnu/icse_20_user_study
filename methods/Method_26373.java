/** 
 * Check that a type-use of an  {@code @ThreadSafe}-annotated type is instantiated with threadsafe type arguments where required by its annotation's containerOf element.
 * @param containerTypeParameters the in-scope threadsafe type parameters, declared on someenclosing class.
 * @param annotation the type's {@code @ThreadSafe} info
 * @param type the type to check
 */
public Violation threadSafeInstantiation(Set<String> containerTypeParameters,AnnotationInfo annotation,Type type){
  if (!annotation.containerOf().isEmpty() && type.tsym.getTypeParameters().size() != type.getTypeArguments().size()) {
    return Violation.of(String.format("'%s' required instantiation of '%s' with type parameters, but was raw",getPrettyName(type.tsym),Joiner.on(", ").join(annotation.containerOf())));
  }
  for (int i=0; i < type.tsym.getTypeParameters().size(); i++) {
    TypeVariableSymbol typaram=type.tsym.getTypeParameters().get(i);
    boolean immutableTypeParameter=hasThreadSafeTypeParameterAnnotation(typaram);
    if (annotation.containerOf().contains(typaram.getSimpleName().toString()) || immutableTypeParameter) {
      Type tyarg=type.getTypeArguments().get(i);
      if (suppressAnnotation != null && tyarg.getAnnotationMirrors().stream().anyMatch(a -> ((ClassSymbol)a.getAnnotationType().asElement()).flatName().contentEquals(suppressAnnotation.getName()))) {
        continue;
      }
      Violation info=isThreadSafeType(!immutableTypeParameter,containerTypeParameters,tyarg);
      if (info.isPresent()) {
        return info.plus(String.format("'%s' was instantiated with %s type for '%s'",getPrettyName(type.tsym),purpose.mutableOrNonThreadSafe(),typaram.getSimpleName()));
      }
    }
  }
  return Violation.absent();
}
