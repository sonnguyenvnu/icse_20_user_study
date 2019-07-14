/** 
 * Triggers the end lifecycle event on each rule in the ruleset. Some rules perform a final summary calculation or cleanup in the end.
 * @param ctx the current context
 */
public void end(RuleContext ctx){
  for (  Rule rule : rules) {
    rule.end(ctx);
  }
}
