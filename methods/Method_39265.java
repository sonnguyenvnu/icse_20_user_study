@Override protected Properties createSessionProperties(){
  final Properties props=super.createSessionProperties();
  props.setProperty(MAIL_POP3_HOST,host);
  props.setProperty(MAIL_POP3_PORT,String.valueOf(port));
  if (authenticator != null) {
    props.setProperty(MAIL_POP3_AUTH,TRUE);
  }
  if (timeout > 0) {
    final String timeoutValue=String.valueOf(timeout);
    props.put(MAIL_POP3_CONNECTIONTIMEOUT,timeoutValue);
    props.put(MAIL_POP3_TIMEOUT,timeoutValue);
  }
  return props;
}
