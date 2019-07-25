/** 
 * Factory method for rule consequences
 */
public static RuleConsequenceProcessor prepare(@NotNull SNode ruleConsequence){
  return new ConsequenceHandler().dispatch(ruleConsequence);
}
