/** 
 * Notify all rules of the start of processing.
 */
public void start(RuleContext ctx){
  for (  RuleSet ruleSet : ruleSets) {
    ruleSet.start(ctx);
  }
}
