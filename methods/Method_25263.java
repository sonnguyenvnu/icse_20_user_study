/** 
 * Returns  {@link Nullness} of the initializer of the {@link VariableTree} at the leaf of thegiven  {@code fieldDeclPath}. Returns  {@link Nullness#NULL} should there be no initializer.
 */
public Nullness getFieldInitializerNullness(TreePath fieldDeclPath,Context context){
  Tree decl=fieldDeclPath.getLeaf();
  checkArgument(decl instanceof VariableTree && ((JCVariableDecl)decl).sym.getKind() == ElementKind.FIELD,"Leaf of fieldDeclPath must be a field declaration: %s",decl);
  ExpressionTree initializer=((VariableTree)decl).getInitializer();
  if (initializer == null) {
    return ((JCVariableDecl)decl).type.isPrimitive() ? Nullness.NONNULL : Nullness.NULL;
  }
  TreePath initializerPath=TreePath.getPath(fieldDeclPath,initializer);
  ClassTree classTree=(ClassTree)fieldDeclPath.getParentPath().getLeaf();
  JavacProcessingEnvironment javacEnv=JavacProcessingEnvironment.instance(context);
  UnderlyingAST ast=new UnderlyingAST.CFGStatement(decl,classTree);
  ControlFlowGraph cfg=CFGBuilder.build(initializerPath,ast,false,false,javacEnv);
  try {
    nullnessPropagation.setContext(context).setCompilationUnit(fieldDeclPath.getCompilationUnit());
    Analysis<Nullness,AccessPathStore<Nullness>,TrustingNullnessPropagation> analysis=new Analysis<>(nullnessPropagation,javacEnv);
    analysis.performAnalysis(cfg);
    return analysis.getValue(initializer);
  }
  finally {
    nullnessPropagation.setContext(null).setCompilationUnit(null);
  }
}
