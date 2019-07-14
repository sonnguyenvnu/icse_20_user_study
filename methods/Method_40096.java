/** 
 * Resolves each element, also construct a result list.
 */
@Nullable static protected List<Type> resolveList(@Nullable Collection<? extends Node> nodes,State s){
  if (nodes == null) {
    return null;
  }
 else {
    List<Type> ret=new ArrayList<>();
    for (    Node n : nodes) {
      ret.add(transformExpr(n,s));
    }
    return ret;
  }
}
