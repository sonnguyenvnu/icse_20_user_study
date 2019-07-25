@Override public EmailConfig find(){
  Optional<EmailConfig> emailConfig=emailRepository.findById(1L);
  if (emailConfig.isPresent()) {
    return emailConfig.get();
  }
 else {
    return new EmailConfig();
  }
}
