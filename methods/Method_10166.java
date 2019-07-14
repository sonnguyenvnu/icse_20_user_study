/** 
 * Sends a HTML mail.
 * @param fromName     the specified from name
 * @param subject      the specified subject
 * @param toMail       the specified receiver mail
 * @param templateName the specified template name
 * @param dataModel    the specified data model
 */
public static void sendHTML(final String fromName,final String subject,final String toMail,final String templateName,final Map<String,Object> dataModel){
  if ("sendcloud".equals(Symphonys.MAIL_CHANNEL)) {
    if (StringUtils.isBlank(Symphonys.MAIL_SENDCLOUD_API_USER) || StringUtils.isBlank(Symphonys.MAIL_SENDCLOUD_API_KEY)) {
      LOGGER.warn("Please configure [#### SendCloud Mail channel ####] section in symphony.properties for sending mail");
      return;
    }
  }
 else   if ("aliyun".equals(Symphonys.MAIL_CHANNEL)) {
    if (StringUtils.isBlank(Symphonys.MAIL_ALIYUN_AK) || StringUtils.isBlank(Symphonys.MAIL_ALIYUN_SK)) {
      LOGGER.warn("Please configure [#### Aliyun Mail channel ####] section in symphony.properties for sending mail");
      return;
    }
  }
 else {
    if (StringUtils.isBlank(MailSender.username) || StringUtils.isBlank(MailSender.password)) {
      LOGGER.warn("Please configure [#### Local Mail channel ####] section in symphony.properties for sending mail");
      return;
    }
  }
  Keys.fillServer(dataModel);
  Keys.fillRuntime(dataModel);
  dataModel.put(Common.YEAR,String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
  try {
    final Template template=TEMPLATE_CFG.getTemplate(templateName + ".ftl");
    final StringWriter stringWriter=new StringWriter();
    template.process(dataModel,stringWriter);
    stringWriter.close();
    final String html=stringWriter.toString();
    final String domain=StringUtils.substringAfter(toMail,"@");
    final String channel=DOMAIN_CHANNEL.getOrDefault(domain,Symphonys.MAIL_CHANNEL);
switch (channel) {
case "aliyun":
      aliSendHtml(Symphonys.MAIL_ALIYUN_FROM,fromName,subject,toMail,html,Symphonys.MAIL_ALIYUN_AK,Symphonys.MAIL_ALIYUN_SK);
    return;
case "local":
  MailSender.getInstance().sendHTML(fromName,subject,toMail,html);
return;
case "sendcloud":
final Map<String,Object> formData=new HashMap<>();
formData.put("apiUser",Symphonys.MAIL_SENDCLOUD_API_USER);
formData.put("apiKey",Symphonys.MAIL_SENDCLOUD_API_KEY);
formData.put("from",Symphonys.MAIL_SENDCLOUD_FROM);
formData.put("fromName",fromName);
formData.put("subject",subject);
formData.put("to",toMail);
formData.put("html",html);
final HttpResponse response=HttpRequest.post("http://api.sendcloud.net/apiv2/mail/send").form(formData).header(Common.USER_AGENT,Symphonys.USER_AGENT_BOT).connectionTimeout(5000).timeout(5000).send();
response.close();
response.charset("UTF-8");
LOGGER.debug(response.bodyText());
return;
default :
LOGGER.error("Unknown mail channel [" + channel + "]");
}
}
 catch (final Exception e) {
LOGGER.log(Level.ERROR,"Sends a mail [subject=" + subject + ", to=" + toMail + "] failed",e);
}
}
