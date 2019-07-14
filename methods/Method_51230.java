/** 
 * @see RuleViolationFactory#addViolation(RuleContext,Rule,Node,String,Object[])
 */
public void addViolation(Object data,Node node,String arg){
  RuleContext ruleContext=(RuleContext)data;
  ruleContext.getLanguageVersion().getLanguageVersionHandler().getRuleViolationFactory().addViolation(ruleContext,this,node,this.getMessage(),new Object[]{arg});
}
