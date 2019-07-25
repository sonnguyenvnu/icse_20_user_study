/** 
 * {@inheritDoc}<p> Default the targetType should be assignable to the returnType and the sourceType to the parameter, excluding generic type variables. When the implementor sees a need for this, this method can be overridden.
 */
@Override public boolean matches(List<Type> sourceTypes,Type targetType){
  if (sourceTypes.size() != 1) {
    return false;
  }
  Type sourceType=first(sourceTypes);
  if (getReturnType().isAssignableTo(targetType.erasure()) && sourceType.erasure().isAssignableTo(getParameter().getType())) {
    return doTypeVarsMatch(sourceType,targetType);
  }
  if (getReturnType().getFullyQualifiedName().equals("java.lang.Object") && sourceType.erasure().isAssignableTo(getParameter().getType())) {
    return doTypeVarsMatch(sourceType,targetType);
  }
  if (getReturnType().isAssignableTo(targetType.erasure()) && getParameter().getType().getFullyQualifiedName().equals("java.lang.Object")) {
    return doTypeVarsMatch(sourceType,targetType);
  }
  return false;
}
