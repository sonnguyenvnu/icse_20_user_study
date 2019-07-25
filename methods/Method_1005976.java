@Override public void configure(RedisConnection connection){
  String notifyOptions=getNotifyOptions(connection);
  String customizedNotifyOptions=notifyOptions;
  if (!customizedNotifyOptions.contains("E")) {
    customizedNotifyOptions+="E";
  }
  boolean A=customizedNotifyOptions.contains("A");
  if (!(A || customizedNotifyOptions.contains("g"))) {
    customizedNotifyOptions+="g";
  }
  if (!(A || customizedNotifyOptions.contains("x"))) {
    customizedNotifyOptions+="x";
  }
  if (!notifyOptions.equals(customizedNotifyOptions)) {
    connection.setConfig(CONFIG_NOTIFY_KEYSPACE_EVENTS,customizedNotifyOptions);
  }
}
