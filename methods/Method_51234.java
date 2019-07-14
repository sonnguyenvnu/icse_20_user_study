/** 
 * @see RuleViolationFactory#addViolation(RuleContext,Rule,Node,String,Object[])
 */
public void addViolationWithMessage(Object data,Node node,String message,Object[] args){
  RuleContext ruleContext=(RuleContext)data;
  ruleContext.getLanguageVersion().getLanguageVersionHandler().getRuleViolationFactory().addViolation(ruleContext,this,node,message,args);
}
