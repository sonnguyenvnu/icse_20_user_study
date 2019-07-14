public static Matcher<ExpressionTree> instanceField(String className,String fieldName){
  return new FieldReferenceMatcher(){
    @Override boolean classIsAppropriate(    ClassSymbol classSymbol){
      return classSymbol.getQualifiedName().contentEquals(className);
    }
    @Override boolean fieldSymbolIsAppropriate(    Symbol symbol){
      return !symbol.isStatic() && symbol.getSimpleName().contentEquals(fieldName);
    }
  }
;
}
