/** 
 * Scope-friendly traversal of an ancestor hierarchy with respect to attribute nodes
 */
public static SNode parent(SNode n){
  if (SNodeOperations.isAttribute(n)) {
    if (SNodeOperations.isInstanceOf(n,MetaAdapterFactory.getConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x2eb1ad060897da54L,"jetbrains.mps.lang.core.structure.NodeAttribute"))) {
      SNode next=SNodeOperations.getPrevSibling(n);
      while (next != null) {
        if (SNodeOperations.isInstanceOf(next,MetaAdapterFactory.getConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x2eb1ad060897da54L,"jetbrains.mps.lang.core.structure.NodeAttribute"))) {
          return next;
        }
        next=SNodeOperations.getPrevSibling(next);
      }
      n=SNodeOperations.getParent(n);
      return (n == null ? null : SNodeOperations.getParent(n));
    }
    return SNodeOperations.getParent(n);
  }
  SNode lastAttr=Sequence.fromIterable(SNodeOperations.ofConcept(SLinkOperations.getChildren(SNodeOperations.cast(n,MetaAdapterFactory.getConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,"jetbrains.mps.lang.core.structure.BaseConcept")),MetaAdapterFactory.getContainmentLink(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x10802efe25aL,0x47bf8397520e5942L,"smodelAttribute")),MetaAdapterFactory.getConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x2eb1ad060897da54L,"jetbrains.mps.lang.core.structure.NodeAttribute"))).last();
  if ((lastAttr != null)) {
    return lastAttr;
  }
  return SNodeOperations.getParent(n);
}
