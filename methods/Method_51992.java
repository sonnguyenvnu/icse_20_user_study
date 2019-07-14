@Override protected MetricsComputer<ASTAnyTypeDeclaration,MethodLikeNode> getLanguageSpecificComputer(){
  return JavaMetricsComputer.getInstance();
}
