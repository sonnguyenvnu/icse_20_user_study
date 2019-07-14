public TypeVar inlineAsVar(UTypeVar var) throws CouldNotResolveImportException {
  TypeVar typeVar=typeVarCache.get(var.getName());
  if (typeVar != null) {
    return typeVar;
  }
  Name name=asName(var.getName());
  TypeSymbol sym=new TypeVariableSymbol(0,name,null,symtab().noSymbol);
  typeVar=new TypeVar(sym,null,symtab().botType);
  sym.type=typeVar;
  typeVarCache.put(var.getName(),typeVar);
  typeVar.bound=var.getUpperBound().inline(this);
  typeVar.lower=var.getLowerBound().inline(this);
  return typeVar;
}
