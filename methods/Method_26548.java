public ClassSymbol resolveClass(CharSequence qualifiedClass) throws CouldNotResolveImportException {
  try {
    Symbol symbol=JavaCompiler.instance(context).resolveIdent(symtab().java_base,qualifiedClass.toString());
    if (symbol.equals(symtab().errSymbol) || !(symbol instanceof ClassSymbol)) {
      throw new CouldNotResolveImportException(qualifiedClass);
    }
 else {
      return (ClassSymbol)symbol;
    }
  }
 catch (  NullPointerException e) {
    throw new CouldNotResolveImportException(qualifiedClass);
  }
}
