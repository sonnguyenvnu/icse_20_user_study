/** 
 * Convert a  {@code String} or {@code File} argument to a {@code Path}. 
 */
private String toPath(VisitorState state,Tree fileArg,SuggestedFix.Builder fix){
  Type type=ASTHelpers.getType(fileArg);
  if (ASTHelpers.isSubtype(type,state.getSymtab().stringType,state)) {
    fix.addImport("java.nio.file.Paths");
    return String.format("Paths.get(%s)",state.getSourceForNode(fileArg));
  }
 else   if (ASTHelpers.isSubtype(type,state.getTypeFromString("java.io.File"),state)) {
    return String.format("%s.toPath()",state.getSourceForNode(fileArg));
  }
 else {
    throw new AssertionError("unexpected type: " + type);
  }
}
