@Override public Symbol resolveIdentifier(IdentifierTree node){
  String name=node.getName().toString();
  if (name.equals("this")) {
    return enclosingClass;
  }
  if (name.equals("itself")) {
    Symbol sym=ASTHelpers.getSymbol(decl);
    if (sym == null) {
      throw new IllegalGuardedBy(decl.getClass().toString());
    }
    return sym;
  }
  Symbol.VarSymbol field=getField(enclosingClass,name);
  if (field != null) {
    return field;
  }
  Symbol type=resolveType(name,SearchSuperTypes.YES);
  if (type != null) {
    return type;
  }
  throw new IllegalGuardedBy(name);
}
