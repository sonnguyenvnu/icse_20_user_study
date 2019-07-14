/** 
 * If the tree is a  {@link JCBlock}, returns a list of disjoint matches corresponding to the exact list of template statements found consecutively; otherwise, returns an empty list.
 */
@Override public Iterable<BlockTemplateMatch> match(JCTree tree,Context context){
  if (tree instanceof JCBlock) {
    JCBlock block=(JCBlock)tree;
    ImmutableList<JCStatement> targetStatements=ImmutableList.copyOf(block.getStatements());
    return matchesStartingAnywhere(block,0,targetStatements,context).first().or(List.<BlockTemplateMatch>nil());
  }
  return ImmutableList.of();
}
