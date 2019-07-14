/** 
 * Notify all rules of the end of processing.
 */
public void end(RuleContext ctx){
  for (  RuleSet ruleSet : ruleSets) {
    ruleSet.end(ctx);
  }
}
