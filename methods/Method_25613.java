private static Matcher<MethodTree> overridesMethodOfClass(final Class<?> clazz){
  checkNotNull(clazz);
  return new Matcher<MethodTree>(){
    @Override public boolean matches(    MethodTree tree,    VisitorState state){
      MethodSymbol symbol=getSymbol(tree);
      if (symbol == null) {
        return false;
      }
      for (      MethodSymbol superMethod : findSuperMethods(symbol,state.getTypes())) {
        if (superMethod.owner != null && superMethod.owner.getQualifiedName().contentEquals(clazz.getName())) {
          return true;
        }
      }
      return false;
    }
  }
;
}
