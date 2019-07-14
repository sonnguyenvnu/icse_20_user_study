/** 
 * Gets a human-friendly name for the given  {@link Symbol} to use in diagnostics. 
 */
public String getPrettyName(Symbol sym){
  if (!sym.getSimpleName().isEmpty()) {
    return sym.getSimpleName().toString();
  }
  if (sym.getKind() == ElementKind.ENUM) {
    return sym.owner.getSimpleName().toString();
  }
  Type superType=state.getTypes().supertype(sym.type);
  if (state.getTypes().isSameType(superType,state.getSymtab().objectType)) {
    superType=Iterables.getFirst(state.getTypes().interfaces(sym.type),superType);
  }
  return superType.tsym.getSimpleName().toString();
}
