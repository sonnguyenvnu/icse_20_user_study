public static int depth(SNode sNode){
  int childDepth=0;
  for (  SNode child : sNode.getChildren()) {
    int depth=depth(child);
    if (childDepth < depth) {
      childDepth=depth;
    }
  }
  if (!SNodeOperations.isInstanceOf(sNode,SNodeUtil.concept_SNodeType))   return childDepth + 1;
  if (sNode.getReference(SNodeUtil.ref_SNodeType_concept) == null)   return childDepth + 1;
  return childDepth + 2;
}
