/** 
 * Is sym a non-static member of an enclosing class of currentClass? 
 */
private static boolean memberOfEnclosing(Symbol owner,VisitorState state,Symbol sym){
  if (sym == null || !sym.hasOuterInstance()) {
    return false;
  }
  for (ClassSymbol encl=owner.owner.enclClass(); encl != null; encl=encl.owner != null ? encl.owner.enclClass() : null) {
    if (sym.isMemberOf(encl,state.getTypes())) {
      return true;
    }
  }
  return false;
}
