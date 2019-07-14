private static List<TypeVariableSymbol> typeVariablesEnclosing(Symbol sym){
  List<TypeVariableSymbol> typeVarScopes=new ArrayList<>();
  outer:   while (!sym.isStatic()) {
    sym=sym.owner;
switch (sym.getKind()) {
case PACKAGE:
      break outer;
case METHOD:
case CLASS:
    typeVarScopes.addAll(0,sym.getTypeParameters());
  break;
default :
}
}
return typeVarScopes;
}
