/** 
 * @see RuleViolationFactory#addViolation(RuleContext,Rule,Node,String,Object[])
 */
public void addViolation(Object data,Node node,Object[] args){
  RuleContext ruleContext=(RuleContext)data;
  ruleContext.getLanguageVersion().getLanguageVersionHandler().getRuleViolationFactory().addViolation(ruleContext,this,node,this.getMessage(),args);
}
