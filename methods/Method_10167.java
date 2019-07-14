/** 
 * Batch send HTML mails.
 * @param fromName     the specified from name
 * @param subject      the specified subject
 * @param toMails      the specified receiver mails
 * @param templateName the specified template name
 * @param dataModel    the specified data model
 */
public static void batchSendHTML(final String fromName,final String subject,final List<String> toMails,final String templateName,final Map<String,Object> dataModel){
  if ("sendcloud".equals(Symphonys.MAIL_CHANNEL)) {
    if (StringUtils.isBlank(Symphonys.MAIL_SENDCLOUD_BATCH_API_USER) || StringUtils.isBlank(Symphonys.MAIL_SENDCLOUD_BATCH_API_KEY)) {
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
    final Map<String,Object> formData=new HashMap<>();
    formData.put("apiUser",Symphonys.MAIL_SENDCLOUD_BATCH_API_USER);
    formData.put("apiKey",Symphonys.MAIL_SENDCLOUD_BATCH_API_KEY);
    formData.put("from",Symphonys.MAIL_SENDCLOUD_BATCH_FROM);
    formData.put("fromName",fromName);
    formData.put("subject",subject);
    formData.put("templateInvokeName",templateName);
    final Template template=TEMPLATE_CFG.getTemplate(templateName + ".ftl");
    final StringWriter stringWriter=new StringWriter();
    template.process(dataModel,stringWriter);
    stringWriter.close();
    final String html=stringWriter.toString();
    if ("sendcloud".equals(Symphonys.MAIL_CHANNEL)) {
      refreshWeeklyTemplate(html);
    }
    int index=0;
    final int size=toMails.size();
    List<String> batch=new ArrayList<>();
    HttpResponse response;
    while (index < size) {
      final String mail=toMails.get(index);
      batch.add(mail);
      index++;
      if (batch.size() > 99) {
        if ("aliyun".equals(Symphonys.MAIL_CHANNEL)) {
          final String toMail=getStringToMailByList(batch);
          aliSendHtml(Symphonys.MAIL_ALIYUN_BATCH_FROM,fromName,subject,toMail,html,Symphonys.MAIL_ALIYUN_AK,Symphonys.MAIL_ALIYUN_SK);
          LOGGER.info("Sent [" + batch.size() + "] mails");
        }
 else         if ("local".equals(Symphonys.MAIL_CHANNEL.toLowerCase())) {
          MailSender.getInstance().sendHTML(fromName,subject,batch,html);
        }
 else {
          try {
            final JSONObject xsmtpapi=new JSONObject();
            xsmtpapi.put("to",batch);
            xsmtpapi.put("sub",new JSONObject());
            formData.put("xsmtpapi",xsmtpapi.toString());
            response=HttpRequest.post("http://api.sendcloud.net/apiv2/mail/sendtemplate").form(formData).header(Common.USER_AGENT,Symphonys.USER_AGENT_BOT).connectionTimeout(5000).timeout(30000).send();
            response.close();
            response.charset("UTF-8");
            LOGGER.debug(response.bodyText());
            LOGGER.info("Sent [" + batch.size() + "] mails");
          }
 catch (          final Exception e) {
            LOGGER.log(Level.ERROR,"Send mail error",e);
          }
        }
        batch.clear();
      }
    }
    if (!batch.isEmpty()) {
      if ("aliyun".equals(Symphonys.MAIL_CHANNEL)) {
        final String toMail=getStringToMailByList(batch);
        aliSendHtml(Symphonys.MAIL_ALIYUN_BATCH_FROM,fromName,subject,toMail,html,Symphonys.MAIL_ALIYUN_AK,Symphonys.MAIL_ALIYUN_SK);
        LOGGER.info("Sent [" + batch.size() + "] mails");
      }
 else       if ("local".equals(Symphonys.MAIL_CHANNEL.toLowerCase())) {
        MailSender.getInstance().sendHTML(fromName,subject,batch,html);
      }
 else {
        try {
          final JSONObject xsmtpapi=new JSONObject();
          xsmtpapi.put("to",batch);
          xsmtpapi.put("sub",new JSONObject());
          formData.put("xsmtpapi",xsmtpapi.toString());
          response=HttpRequest.post("http://api.sendcloud.net/apiv2/mail/sendtemplate").form(formData).header(Common.USER_AGENT,Symphonys.USER_AGENT_BOT).connectionTimeout(5000).timeout(30000).send();
          response.close();
          response.charset("UTF-8");
          LOGGER.debug(response.bodyText());
          LOGGER.info("Sent [" + batch.size() + "] mails");
        }
 catch (        final Exception e) {
          LOGGER.log(Level.ERROR,"Send mail error",e);
        }
      }
    }
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Batch send mail error",e);
  }
}
