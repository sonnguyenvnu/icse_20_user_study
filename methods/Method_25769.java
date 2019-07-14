public static CanBeStaticResult canBeStaticResult(Tree tree,Symbol owner,VisitorState state){
  CanBeStaticAnalyzer scanner=new CanBeStaticAnalyzer(owner,state);
  ((JCTree)tree).accept(scanner);
  return CanBeStaticResult.of(scanner.canPossiblyBeStatic,scanner.outerReferences);
}
