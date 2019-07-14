private static String suggestedSingleLetter(String id,Tree tree){
  char firstLetter=id.charAt(0);
  Symbol sym=ASTHelpers.getSymbol(tree);
  List<TypeVariableSymbol> enclosingTypeSymbols=typeVariablesEnclosing(sym);
  for (  TypeVariableSymbol typeName : enclosingTypeSymbols) {
    char enclosingTypeFirstLetter=typeName.toString().charAt(0);
    if (enclosingTypeFirstLetter == firstLetter && !TypeParameterNamingClassification.classify(typeName.name.toString()).isValidName()) {
      ImmutableList<String> typeVarsInScope=Streams.concat(enclosingTypeSymbols.stream(),sym.getTypeParameters().stream()).map(v -> v.name.toString()).collect(toImmutableList());
      return firstLetterReplacementName(id,typeVarsInScope);
    }
  }
  return Character.toString(firstLetter);
}
