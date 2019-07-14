/** 
 * Returns true if the given source file should be excluded from analysis. 
 */
private boolean shouldExcludeSourceFile(CompilationUnitTree tree){
  Pattern excludedPattern=errorProneOptions.getExcludedPattern();
  return excludedPattern != null && excludedPattern.matcher(ASTHelpers.getFileName(tree)).matches();
}
