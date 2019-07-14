@Override protected Properties createSessionProperties(){
  final Properties props=super.createSessionProperties();
  props.setProperty(MAIL_TRANSPORT_PROTOCOL,PROTOCOL_SMTP);
  props.setProperty(MAIL_HOST,host);
  props.setProperty(MAIL_SMTP_HOST,host);
  props.setProperty(MAIL_SMTP_PORT,String.valueOf(port));
  if (authenticator != null) {
    props.setProperty(MAIL_SMTP_AUTH,TRUE);
  }
  if (timeout > 0) {
    final String timeoutValue=String.valueOf(timeout);
    props.put(MAIL_SMTP_CONNECTIONTIMEOUT,timeoutValue);
    props.put(MAIL_SMTP_TIMEOUT,timeoutValue);
    props.put(MAIL_SMTP_WRITETIMEOUT,timeoutValue);
  }
  return props;
}
