@Nullable private Nullness fieldInitializerNullnessIfAvailable(ClassAndField accessed){
  if (!traversed.add(accessed.symbol)) {
    return NULL;
  }
  try {
    JavacProcessingEnvironment javacEnv=JavacProcessingEnvironment.instance(context);
    TreePath fieldDeclPath=Trees.instance(javacEnv).getPath(accessed.symbol);
    if (fieldDeclPath == null || fieldDeclPath.getCompilationUnit() != compilationUnit || !(fieldDeclPath.getLeaf() instanceof VariableTree)) {
      return null;
    }
    ExpressionTree initializer=((VariableTree)fieldDeclPath.getLeaf()).getInitializer();
    if (initializer == null) {
      return null;
    }
    ClassTree classTree=(ClassTree)fieldDeclPath.getParentPath().getLeaf();
    TreePath initializerPath=TreePath.getPath(fieldDeclPath,initializer);
    UnderlyingAST ast=new UnderlyingAST.CFGStatement(initializerPath.getLeaf(),classTree);
    ControlFlowGraph cfg=CFGBuilder.build(initializerPath,ast,false,false,javacEnv);
    Analysis<Nullness,AccessPathStore<Nullness>,NullnessPropagationTransfer> analysis=new Analysis<>(this,javacEnv);
    analysis.performAnalysis(cfg);
    return analysis.getValue(initializerPath.getLeaf());
  }
  finally {
    traversed.remove(accessed.symbol);
  }
}
