private static String dump(Collection<ProtocolConfiguration> configs){
  StringBuilder sb=new StringBuilder();
  String indent="  ";
  sb.append("<config>\n");
  for (  ProtocolConfiguration cfg : configs) {
    sb.append(indent).append("<").append(cfg.getProtocolName());
    Map<String,String> props=cfg.getProperties();
    if (props.isEmpty()) {
      sb.append(" />\n");
    }
 else {
      sb.append("\n").append(indent).append(indent);
      for (      Map.Entry<String,String> entry : props.entrySet()) {
        String key=entry.getKey();
        String val=entry.getValue();
        key=trim(key);
        val=trim(val);
        sb.append(key).append("=\"").append(val).append("\" ");
      }
      sb.append(" />\n");
    }
  }
  sb.append("</config>\n");
  return sb.toString();
}
