/** 
 * Adds a daa violation to the report.
 */
private void addDaaViolation(Object data,Node node,String type,String var,int startLine,int endLine){
  if (!maxNumberOfViolationsReached() && !violationAlreadyExists(type,var,startLine,endLine) && node != null) {
    RuleContext ctx=(RuleContext)data;
    String msg=type;
    if (getMessage() != null) {
      msg=MessageFormat.format(getMessage(),type,var,startLine,endLine);
    }
    DaaRuleViolation violation=new DaaRuleViolation(this,ctx,node,type,msg,var,startLine,endLine);
    ctx.getReport().addRuleViolation(violation);
    daaRuleViolations.add(violation);
    currentRuleViolationCount++;
  }
}
