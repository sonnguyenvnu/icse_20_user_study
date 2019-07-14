/** 
 * ??????
 * @param sender
 * @param sendto
 * @param templateName
 * @param o
 */
@Async public void sendTemplateMail(String sender,String sendto,String title,String templateName,Object o){
  log.info("???" + sendto + "????");
  MimeMessage message=mailSender.createMimeMessage();
  try {
    MimeMessageHelper helper=new MimeMessageHelper(message,true);
    helper.setFrom(sender);
    helper.setTo(sendto);
    helper.setSubject(title);
    Context context=new Context();
    context.setVariable("title",title);
    context.setVariables(StringUtils.beanToMap(o));
    String content=templateEngine.process(templateName,context);
    helper.setText(content,true);
    mailSender.send(message);
    log.info("?" + sendto + "??????");
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
