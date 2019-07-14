/** 
 * ????????????.
 * @param message
 * @param from
 * @param to
 * @param subject
 * @param content
 * @return
 * @throws Exception
 */
public MimeMessage createTextMail(MimeMessage message,String from,String[] to,String subject,String content) throws Exception {
  message.setFrom(new InternetAddress(from));
  setTo(to,message);
  if (StringUtils.isEmpty(subject)) {
    subject="email from the system";
  }
  message.setSubject(StringUtils.trimToEmpty(subject));
  message.setContent(content,CHARSET);
  return message;
}
