/** 
 * Resolves a simple name as a type. Considers super classes, lexically enclosing classes, and then arbitrary types available in the current environment.
 */
private Symbol resolveType(String name,SearchSuperTypes searchSuperTypes){
  Symbol type=null;
  if (searchSuperTypes == SearchSuperTypes.YES) {
    type=getSuperType(enclosingClass,name);
  }
  if (enclosingClass.getSimpleName().contentEquals(name)) {
    type=enclosingClass;
  }
  if (type == null) {
    type=getLexicallyEnclosing(enclosingClass,name);
  }
  if (type == null) {
    type=attribIdent(name);
  }
  checkGuardedBy(!(type instanceof Symbol.PackageSymbol),"All we could find for '%s' was a package symbol.",name);
  return type;
}
