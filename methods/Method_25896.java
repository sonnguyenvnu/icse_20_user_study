/** 
 * Get overridden @ForOverride methods.
 * @param state the VisitorState
 * @param method the method to find overrides for
 * @return a list of methods annotated @ForOverride that the method overrides, including themethod itself if it has the annotation
 */
private List<MethodSymbol> getOverriddenMethods(VisitorState state,MethodSymbol method){
  if (method.isStatic()) {
    throw new IllegalArgumentException("getOverriddenMethods may not be called on a static method");
  }
  List<MethodSymbol> list=new LinkedList<>();
  list.add(method);
  Type currType=state.getTypes().supertype(method.owner.type);
  while (currType != null && currType.tsym != null && !currType.tsym.equals(state.getSymtab().objectType.tsym) && !ASTHelpers.isSameType(currType,Type.noType,state)) {
    Symbol sym=currType.tsym.members().findFirst(method.name);
    if (sym instanceof MethodSymbol) {
      list.add((MethodSymbol)sym);
    }
    currType=state.getTypes().supertype(currType);
  }
  Iterator<MethodSymbol> iter=list.iterator();
  while (iter.hasNext()) {
    MethodSymbol member=iter.next();
    if (!hasAnnotation(FOR_OVERRIDE,member) || !method.overrides(member,(TypeSymbol)member.owner,state.getTypes(),true)) {
      iter.remove();
    }
  }
  return list;
}
