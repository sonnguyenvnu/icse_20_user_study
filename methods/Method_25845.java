/** 
 * Converts a  {@code String} to a {@code File}. 
 */
private Object toFile(VisitorState state,Tree fileArg,SuggestedFix.Builder fix){
  Type type=ASTHelpers.getType(fileArg);
  if (ASTHelpers.isSubtype(type,state.getSymtab().stringType,state)) {
    fix.addImport("java.io.File");
    return String.format("new File(%s)",state.getSourceForNode(fileArg));
  }
 else   if (ASTHelpers.isSubtype(type,state.getTypeFromString("java.io.File"),state)) {
    return state.getSourceForNode(fileArg);
  }
 else {
    throw new AssertionError("unexpected type: " + type);
  }
}
