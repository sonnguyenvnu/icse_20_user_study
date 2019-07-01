private AlertEmailGenerator _XXXXX_(Map<String,Object> notificationConfig){
  String tplFileName=(String)notificationConfig.get(PublishConstants.TEMPLATE);
  if (tplFileName == null || tplFileName.equals("")) {
    tplFileName="ALERT_INLINED_TEMPLATE.vm";
  }
  String subject=(String)notificationConfig.get(PublishConstants.SUBJECT);
  if (subject == null) {
    subject="No subject";
  }
  String sender=(String)notificationConfig.get(PublishConstants.SENDER);
  String recipients=(String)notificationConfig.get(PublishConstants.RECIPIENTS);
  if (sender == null || recipients == null) {
    LOG.warn("email sender or recipients is null");
    return null;
  }
  AlertEmailGenerator gen=AlertEmailGeneratorBuilder.newBuilder().withMailProps(this.mailClientProperties).withSubject(subject).withSender(sender).withRecipients(recipients).withTplFile(tplFileName).withExecutorPool(this.executorPool).withServerHost(this.serverHost).withServerPort(this.serverPort).build();
  return gen;
}