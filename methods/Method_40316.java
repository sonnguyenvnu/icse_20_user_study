/** 
 * Utility method to resolve every node in  {@code nodes} andreturn the union of their types.  If  {@code nodes} is empty or{@code null}, returns a new  {@link org.python.indexer.types.UnknownType}.
 * @throws Exception 
 */
protected Type resolveListAsUnion(List<? extends Node> nodes,Scope s,int tag) throws Exception {
  if (nodes == null || nodes.isEmpty()) {
    return Indexer.idx.builtins.unknown;
  }
  Type result=null;
  for (  Node node : nodes) {
    Type nodeType=resolveExpr(node,s,tag);
    if (result == null) {
      result=nodeType;
    }
 else {
      result=UnionType.union(result,nodeType);
    }
  }
  return result;
}
