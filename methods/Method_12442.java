@Override protected Mono<Void> doNotify(InstanceEvent event,Instance instance){
  return Mono.fromRunnable(() -> {
    Context ctx=new Context();
    ctx.setVariables(additionalProperties);
    ctx.setVariable("baseUrl",this.baseUrl);
    ctx.setVariable("event",event);
    ctx.setVariable("instance",instance);
    ctx.setVariable("lastStatus",getLastStatus(event.getInstance()));
    try {
      MimeMessage mimeMessage=mailSender.createMimeMessage();
      MimeMessageHelper message=new MimeMessageHelper(mimeMessage,StandardCharsets.UTF_8.name());
      message.setText(getBody(ctx).replaceAll("\\s+\\n","\n"),true);
      message.setSubject(getSubject(ctx));
      message.setTo(this.to);
      message.setCc(this.cc);
      message.setFrom(this.from);
      mailSender.send(mimeMessage);
    }
 catch (    MessagingException ex) {
      throw new RuntimeException("Error sending mail notification",ex);
    }
  }
);
}
