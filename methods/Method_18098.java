/** 
 * Set the nested tree before measuring it in order to transfer over important information such as layout direction needed during measurement.
 */
@Override public void setNestedTree(InternalNode nestedTree){
  if (nestedTree != NULL_LAYOUT) {
    nestedTree.getOrCreateNestedTreeProps().mNestedTreeHolder=this;
  }
  getOrCreateNestedTreeProps().mNestedTree=nestedTree;
}
