/** 
 * Return whether the given try-tree will catch the given exception type. 
 */
private boolean tryCatchesException(TryTree tryTree,Type exceptionToCatch,VisitorState state){
  Types types=state.getTypes();
  return tryTree.getCatches().stream().anyMatch((  CatchTree catchClause) -> {
    Type catchesException=getType(catchClause.getParameter().getType());
    if (catchesException != null && catchesException.isUnion()) {
      return Streams.stream(((UnionClassType)catchesException).getAlternativeTypes()).anyMatch(caught -> types.isSuperType(caught,exceptionToCatch));
    }
    return types.isSuperType(catchesException,exceptionToCatch);
  }
);
}
