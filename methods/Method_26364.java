/** 
 * Returns the type of the first superclass or superinterface in the hierarchy annotated with {@code @Immutable}, or  {@code null} if no such super type exists.
 */
private Type immutableSupertype(Symbol sym,VisitorState state){
  for (  Type superType : state.getTypes().closure(sym.type)) {
    if (superType.tsym.equals(sym.type.tsym)) {
      continue;
    }
    if (immutableAnnotations.stream().anyMatch(annotation -> ASTHelpers.hasAnnotation(superType.tsym,annotation,state))) {
      return superType;
    }
  }
  return null;
}
