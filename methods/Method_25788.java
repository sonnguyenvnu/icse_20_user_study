private static boolean referencesIdentifierWithName(final String name,ExpressionTree tree,VisitorState state){
  Matcher<IdentifierTree> identifierMatcher=new Matcher<IdentifierTree>(){
    @Override public boolean matches(    IdentifierTree tree,    VisitorState state){
      return isIdentifierWithName(tree,name);
    }
  }
;
  return hasIdentifier(identifierMatcher).matches(tree,state);
}
