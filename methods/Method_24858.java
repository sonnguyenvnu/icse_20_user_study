private static CompilationUnit makeAST(ASTParser parser,char[] source,Map<String,String> options){
  parser.setSource(source);
  parser.setKind(ASTParser.K_COMPILATION_UNIT);
  parser.setCompilerOptions(options);
  parser.setStatementsRecovery(true);
  return (CompilationUnit)parser.createAST(null);
}
