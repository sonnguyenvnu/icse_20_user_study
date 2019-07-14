/** 
 * Check that the super-type of a  {@code @ThreadSafe}-annotated type is instantiated with threadsafe type arguments where required by its annotation's containerOf element, and that any type arguments that correspond to containerOf type parameters on the sub-type are also in the super-type's containerOf spec.
 * @param containerTypeParameters the in-scope threadsafe type parameters, declared on someenclosing class.
 * @param annotation the type's {@code @ThreadSafe} info
 * @param type the type to check
 */
public Violation checkSuperInstantiation(Set<String> containerTypeParameters,AnnotationInfo annotation,Type type){
  Violation info=threadSafeInstantiation(containerTypeParameters,annotation,type);
  if (info.isPresent()) {
    return info;
  }
  return Streams.zip(type.asElement().getTypeParameters().stream(),type.getTypeArguments().stream(),(typaram,argument) -> {
    if (containerOfSubtyping(containerTypeParameters,annotation,typaram,argument)) {
      return Violation.of(String.format("'%s' is not a container of '%s'",annotation.typeName(),typaram));
    }
    return Violation.absent();
  }
).filter(Violation::isPresent).findFirst().orElse(Violation.absent());
}
