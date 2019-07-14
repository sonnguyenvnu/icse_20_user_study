/** 
 * Convert a boolean append mode to a StandardOpenOption. 
 */
private String toAppendMode(SuggestedFix.Builder fix,Tree appendArg,VisitorState state){
  Boolean value=ASTHelpers.constValue(appendArg,Boolean.class);
  if (value != null) {
    if (value) {
      fix.addStaticImport("java.nio.file.StandardOpenOption.APPEND");
      fix.addStaticImport("java.nio.file.StandardOpenOption.CREATE");
      return ", CREATE, APPEND";
    }
 else {
      return "";
    }
  }
  fix.addImport("java.nio.file.StandardOpenOption");
  fix.addStaticImport("java.nio.file.StandardOpenOption.APPEND");
  fix.addStaticImport("java.nio.file.StandardOpenOption.CREATE");
  return String.format(", %s ? new StandardOpenOption[] {CREATE, APPEND} : new StandardOpenOption[] {CREATE}",state.getSourceForNode(appendArg));
}
