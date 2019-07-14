private static HeldLockSet handleMonitorGuards(VisitorState state,HeldLockSet locks){
  JCNewClass newClassTree=ASTHelpers.findEnclosingNode(state.getPath(),JCNewClass.class);
  if (newClassTree == null) {
    return locks;
  }
  Symbol clazzSym=ASTHelpers.getSymbol(newClassTree.clazz);
  if (!(clazzSym instanceof ClassSymbol)) {
    return locks;
  }
  if (!((ClassSymbol)clazzSym).fullname.contentEquals(MONITOR_GUARD_CLASS)) {
    return locks;
  }
  Optional<GuardedByExpression> lockExpression=GuardedByBinder.bindExpression(Iterables.getOnlyElement(newClassTree.getArguments()),state);
  if (!lockExpression.isPresent()) {
    return locks;
  }
  return locks.plus(lockExpression.get());
}
