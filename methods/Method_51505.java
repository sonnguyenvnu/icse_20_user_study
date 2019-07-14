private void createTimestampAttr(StringBuilder buffer){
  buffer.append(" timestamp=\"").append(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date())).append('"');
}
