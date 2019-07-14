/** 
 * Determines whether a method can be overridden.
 * @return true if the method can be overridden.
 */
public static boolean methodCanBeOverridden(MethodSymbol methodSymbol){
  if (methodSymbol.getModifiers().contains(Modifier.ABSTRACT)) {
    return true;
  }
  if (methodSymbol.isStatic() || methodSymbol.isPrivate() || isFinal(methodSymbol) || methodSymbol.isConstructor()) {
    return false;
  }
  ClassSymbol classSymbol=(ClassSymbol)methodSymbol.owner;
  return !isFinal(classSymbol) && !classSymbol.isAnonymous();
}
