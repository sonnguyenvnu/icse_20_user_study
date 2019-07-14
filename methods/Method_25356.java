public static Matcher<ExpressionTree> anyFieldInClass(String className){
  return new FieldReferenceMatcher(){
    @Override boolean classIsAppropriate(    ClassSymbol classSymbol){
      return classSymbol.getQualifiedName().contentEquals(className);
    }
    @Override boolean fieldSymbolIsAppropriate(    Symbol symbol){
      return true;
    }
  }
;
}
