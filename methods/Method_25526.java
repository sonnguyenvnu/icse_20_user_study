/** 
 * Returns the method tree that matches the given symbol within the compilation unit, or null if none was found.
 */
@Nullable public static MethodTree findMethod(MethodSymbol symbol,VisitorState state){
  return JavacTrees.instance(state.context).getTree(symbol);
}
