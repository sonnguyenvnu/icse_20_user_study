/** 
 * @see RuleViolationFactory#addViolation(RuleContext,Rule,Node,String,Object[])
 */
public void addViolationWithMessage(Object data,Node node,String message,int beginLine,int endLine){
  RuleContext ruleContext=(RuleContext)data;
  ruleContext.getLanguageVersion().getLanguageVersionHandler().getRuleViolationFactory().addViolation(ruleContext,this,node,message,beginLine,endLine,null);
}
