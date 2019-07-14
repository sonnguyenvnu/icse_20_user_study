public void initializeWith(ASTInput node){
  ScopeAndDeclarationFinder sc=new ScopeAndDeclarationFinder();
  node.jjtAccept(sc,null);
  OccurrenceFinder of=new OccurrenceFinder();
  node.jjtAccept(of,null);
}
