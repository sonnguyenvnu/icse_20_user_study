@Override public boolean respond(JiraServiceLevelAgreementActionConfiguration actionConfiguration,ServiceLevelAssessment assessment,Alert a){
  String desc=ServiceLevelAssessmentAlertUtil.getDescription(assessment);
  String projectKey=actionConfiguration.getProjectKey();
  String issueType=actionConfiguration.getIssueType();
  String assignee=actionConfiguration.getAssignee();
  if (jiraClient.isHostConfigured()) {
    Issue issue=new IssueBuilder(projectKey,issueType).setAssignee(assignee).setDescription(desc).setSummary("JIRA for " + assessment.getAgreement().getName()).build();
    log.info("Generating Jira issue: \"{}\"",issue.getSummary());
    log.debug("Jira description: {}",issue.getDescription());
    try {
      jiraClient.createIssue(issue);
    }
 catch (    JiraException e) {
      log.error("Unable to create Jira Issue ",e);
    }
  }
 else {
    log.debug("Jira is not configured.  Issue will not be generated: \"{}\"",desc);
  }
  return true;
}
