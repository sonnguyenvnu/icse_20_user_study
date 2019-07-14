/** 
 * Generate a CodeClimateIssue suitable for processing into JSON from the given RuleViolation.
 * @param rv RuleViolation to convert.
 * @return The generated issue.
 */
private CodeClimateIssue asIssue(RuleViolation rv){
  CodeClimateIssue issue=new CodeClimateIssue();
  issue.check_name=rule.getName();
  issue.description=cleaned(rv.getDescription());
  issue.content=new CodeClimateIssue.Content(BODY_PLACEHOLDER);
  issue.location=getLocation(rv);
  issue.remediation_points=getRemediationPoints();
  issue.categories=getCategories();
switch (rule.getPriority()) {
case HIGH:
    issue.severity="critical";
  break;
case MEDIUM_HIGH:
case MEDIUM:
case MEDIUM_LOW:
issue.severity="normal";
break;
case LOW:
default :
issue.severity="info";
break;
}
return issue;
}
