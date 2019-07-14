/** 
 * Check if the method declares or inherits an implementation of .equals() 
 */
public static boolean implementsEquals(Type type,VisitorState state){
  Name equalsName=state.getName("equals");
  Symbol objectEquals=getOnlyMember(state,state.getSymtab().objectType,"equals");
  for (  Type sup : state.getTypes().closure(type)) {
    if (sup.tsym.isInterface()) {
      continue;
    }
    if (ASTHelpers.isSameType(sup,state.getSymtab().objectType,state)) {
      return false;
    }
    Scope scope=sup.tsym.members();
    if (scope == null) {
      continue;
    }
    for (    Symbol sym : scope.getSymbolsByName(equalsName)) {
      if (sym.overrides(objectEquals,type.tsym,state.getTypes(),false)) {
        return true;
      }
    }
  }
  return false;
}
