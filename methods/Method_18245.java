/** 
 * Check that the root of the nested tree we are going to use, has valid layout directions with its main tree holder node.
 */
static boolean hasValidLayoutDirectionInNestedTree(InternalNode holder,InternalNode nestedTree){
  return nestedTree.isLayoutDirectionInherit() || (nestedTree.getResolvedLayoutDirection() == holder.getResolvedLayoutDirection());
}
