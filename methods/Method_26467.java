static SuggestedFix renameTypeVariable(TypeParameterTree typeParameter,Tree owningTree,String typeVarReplacement,VisitorState state){
  Symbol typeVariableSymbol=ASTHelpers.getSymbol(typeParameter);
  String name=typeParameter.getName().toString();
  int pos=((JCTree)typeParameter).getStartPosition();
  SuggestedFix.Builder fixBuilder=SuggestedFix.builder().replace(pos,pos + name.length(),typeVarReplacement);
  ((JCTree)owningTree).accept(new TreeScanner(){
    @Override public void visitIdent(    JCTree.JCIdent tree){
      Symbol identSym=ASTHelpers.getSymbol(tree);
      if (Objects.equal(identSym,typeVariableSymbol)) {
        if (Objects.equal(state.getSourceForNode(tree),name)) {
          fixBuilder.replace(tree,typeVarReplacement);
        }
      }
    }
  }
);
  DCDocComment docCommentTree=(DCDocComment)JavacTrees.instance(state.context).getDocCommentTree(state.getPath());
  if (docCommentTree != null) {
    docCommentTree.accept(new DocTreeScanner<Void,Void>(){
      @Override public Void visitParam(      ParamTree paramTree,      Void unused){
        if (paramTree.isTypeParameter() && paramTree.getName().getName().contentEquals(name)) {
          DocSourcePositions positions=JavacTrees.instance(state.context).getSourcePositions();
          CompilationUnitTree compilationUnitTree=state.getPath().getCompilationUnit();
          int startPos=(int)positions.getStartPosition(compilationUnitTree,docCommentTree,paramTree.getName());
          int endPos=(int)positions.getEndPosition(compilationUnitTree,docCommentTree,paramTree.getName());
          fixBuilder.replace(startPos,endPos,typeVarReplacement);
        }
        return super.visitParam(paramTree,null);
      }
    }
,null);
  }
  return fixBuilder.build();
}
