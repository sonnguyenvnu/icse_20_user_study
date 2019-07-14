/** 
 * Apply the RuleChain to the given Nodes using the given RuleContext, for those rules using the given Language.
 * @param nodes The Nodes.
 * @param ctx The RuleContext.
 * @param language The Language.
 */
public void apply(List<Node> nodes,RuleContext ctx,Language language){
  RuleChainVisitor visitor=getRuleChainVisitor(language);
  if (visitor != null) {
    visitor.visitAll(nodes,ctx);
  }
}
