public boolean respondx(EmailServiceLevelAgreementActionConfiguration actionConfiguration,ServiceLevelAssessment assessment,Alert a){
  log.info("Responding to SLA violation.");
  String desc=ServiceLevelAssessmentAlertUtil.getDescription(assessment);
  String slaName=assessment.getAgreement().getName();
  String emails=actionConfiguration.getEmailAddresses();
  sendToAddresses(desc,slaName,emails);
  return true;
}
