@Override public Object visit(ASTTypeMethod node,Object data){
  LOGGER.entering(CLASS_PATH,"visit(ASTTypeMethod)");
  if (LOGGER.isLoggable(Level.FINEST)) {
    LOGGER.finest("visit(ASTTypeMethod): " + node.getClass().getCanonicalName() + " @ line " + node.getBeginLine() + ", column " + node.getBeginColumn() + " --- " + new Throwable().getStackTrace());
  }
  sbf.buildDataFlowFor(node);
  vav.compute(node);
  LOGGER.exiting(CLASS_PATH,"visit(ASTTypeMethod)");
  return data;
}
