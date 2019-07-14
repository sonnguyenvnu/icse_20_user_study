/** 
 * Returns an enclosing instance for the specified type if it is thread-safe. 
 */
public Type mutableEnclosingInstance(Optional<ClassTree> tree,ClassType type){
  if (tree.isPresent() && !CanBeStaticAnalyzer.referencesOuter(tree.get(),ASTHelpers.getSymbol(tree.get()),state)) {
    return null;
  }
  Type enclosing=type.getEnclosingType();
  while (!Type.noType.equals(enclosing)) {
    if (getMarkerOrAcceptedAnnotation(enclosing.tsym,state) == null && isThreadSafeType(false,ImmutableSet.of(),enclosing).isPresent()) {
      return enclosing;
    }
    enclosing=enclosing.getEnclosingType();
  }
  return null;
}
