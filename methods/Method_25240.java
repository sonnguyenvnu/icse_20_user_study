/** 
 * Returns any declared or implied bound for the given type variable, meaning this returns any annotation on the given type variable and otherwise returns  {@link #fromDefaultAnnotations} tofind any default in scope of the given type variable.
 */
public static Optional<Nullness> getUpperBound(TypeVariable typeVar){
  Optional<Nullness> result;
  if (typeVar.getUpperBound() instanceof IntersectionType) {
    result=fromAnnotationStream(((IntersectionType)typeVar.getUpperBound()).getBounds().stream().map(TypeMirror::getAnnotationMirrors).map(Object::toString));
  }
 else {
    result=fromAnnotationsOn(typeVar.getUpperBound());
  }
  if (result.isPresent()) {
    return result;
  }
  if (typeVar.asElement().getKind() == TYPE_PARAMETER) {
    Element genericElt=((TypeParameterElement)typeVar.asElement()).getGenericElement();
    if (genericElt.getKind().isClass() || genericElt.getKind().isInterface() || genericElt.getKind() == ElementKind.METHOD) {
      result=((Parameterizable)genericElt).getTypeParameters().stream().filter(typeParam -> typeParam.getSimpleName().equals(typeVar.asElement().getSimpleName())).findFirst().flatMap(decl -> fromAnnotationList(decl.getAnnotationMirrors()));
    }
  }
  return result.isPresent() ? result : fromDefaultAnnotations(typeVar.asElement());
}
