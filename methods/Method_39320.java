@Override protected Properties createSessionProperties(){
  final Properties props=super.createSessionProperties();
  props.setProperty(MAIL_SMTP_STARTTLS_REQUIRED,startTlsRequired ? StringPool.TRUE : StringPool.FALSE);
  props.setProperty(MAIL_SMTP_STARTTLS_ENABLE,StringPool.TRUE);
  props.setProperty(MAIL_SMTP_SOCKET_FACTORY_PORT,String.valueOf(port));
  props.setProperty(MAIL_SMTP_PORT,String.valueOf(port));
  if (!plaintextOverTLS) {
    props.setProperty(MAIL_SMTP_SOCKET_FACTORY_CLASS,"javax.net.ssl.SSLSocketFactory");
  }
  props.setProperty(MAIL_SMTP_SOCKET_FACTORY_FALLBACK,StringPool.FALSE);
  props.setProperty(MAIL_HOST,host);
  return props;
}
