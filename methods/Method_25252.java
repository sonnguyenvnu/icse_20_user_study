@Nullable private static ClassAndField tryGetFieldSymbol(Tree tree){
  Symbol symbol=tryGetSymbol(tree);
  if (symbol instanceof VarSymbol) {
    return ClassAndField.make((VarSymbol)symbol);
  }
  return null;
}
