/** 
 * Returns the erasure of the given type tree, i.e.  {@code List} for {@code List<Foo>}. 
 */
public static Tree getErasedTypeTree(Tree tree){
  return tree.accept(new SimpleTreeVisitor<Tree,Void>(){
    @Override public Tree visitIdentifier(    IdentifierTree tree,    Void unused){
      return tree;
    }
    @Override public Tree visitParameterizedType(    ParameterizedTypeTree tree,    Void unused){
      return tree.getType();
    }
  }
,null);
}
