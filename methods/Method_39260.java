@Override protected Properties createSessionProperties(){
  final Properties props=super.createSessionProperties();
  props.setProperty(MAIL_IMAP_HOST,host);
  props.setProperty(MAIL_IMAP_PORT,String.valueOf(port));
  props.setProperty(MAIL_IMAP_PARTIALFETCH,StringPool.FALSE);
  if (timeout > 0) {
    final String timeoutValue=String.valueOf(timeout);
    props.put(MAIL_IMAP_CONNECTIONTIMEOUT,timeoutValue);
    props.put(MAIL_IMAP_TIMEOUT,timeoutValue);
  }
  return props;
}
