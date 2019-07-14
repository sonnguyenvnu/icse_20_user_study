@Override protected Properties createSessionProperties(){
  final Properties props=super.createSessionProperties();
  props.setProperty(MAIL_IMAP_SOCKET_FACTORY_PORT,String.valueOf(port));
  props.setProperty(MAIL_IMAP_SOCKET_FACTORY_CLASS,"javax.net.ssl.SSLSocketFactory");
  props.setProperty(MAIL_IMAP_SOCKET_FACTORY_FALLBACK,StringPool.FALSE);
  return props;
}
