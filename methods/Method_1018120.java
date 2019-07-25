@Override public boolean respond(EmailServiceLevelAgreementActionConfiguration actionConfiguration,ServiceLevelAssessment assessment,Alert a){
  log.info("Responding to SLA violation {}. for alert {} received from: {} ",assessment.getServiceLevelAgreementDescription().getName(),a.getId(),a.getSource());
  String desc=ServiceLevelAssessmentAlertUtil.getDescription(assessment);
  String slaName=assessment.getAgreement().getName();
  String emails=actionConfiguration.getEmailAddresses();
  String[] addresses=emails.split(",");
  String subject="SLA Violated: " + slaName;
  String body=desc;
  VelocityEmailTemplate emailTemplate=parseVelocityTemplate(actionConfiguration,assessment,a);
  if (emailTemplate == null) {
    body=desc;
  }
 else {
    subject=emailTemplate.getSubject();
    body=emailTemplate.getBody();
  }
  final String finalSubject=subject;
  final String finalBody=body;
  log.info("sending {}  email to: {}",assessment.getServiceLevelAgreementDescription().getName(),addresses);
  Arrays.stream(addresses).forEach(address -> {
    emailService.sendMail(address.trim(),finalSubject,finalBody);
  }
);
  return true;
}
