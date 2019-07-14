@PostMapping("/provider/email-to/{email}") public boolean email(@PathVariable("email") String email,@RequestBody TxException txEx){
  if (Objects.isNull(javaMailSender)) {
    log.error("non admin mail configured. so tx exception not be send to email:" + email);
    return false;
  }
  SimpleMailMessage message=new SimpleMailMessage();
  message.setFrom(mailProperties.getUsername());
  message.setTo(email);
  message.setSubject("TX-LCN Transaction Exception!");
  message.setText(JSON.toJSONString(txEx));
  javaMailSender.send(message);
  return true;
}
