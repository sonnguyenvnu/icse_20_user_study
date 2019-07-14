private static CompilationUnit makeASTWithBindings(ASTParser parser,char[] source,Map<String,String> options,String className,String[] classPath){
  parser.setSource(source);
  parser.setKind(ASTParser.K_COMPILATION_UNIT);
  parser.setCompilerOptions(options);
  parser.setStatementsRecovery(true);
  parser.setUnitName(className);
  parser.setEnvironment(classPath,null,null,false);
  parser.setResolveBindings(true);
  return (CompilationUnit)parser.createAST(null);
}
