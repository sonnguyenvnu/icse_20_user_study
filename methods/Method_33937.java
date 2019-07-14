private static void appendField(StringBuilder builder,String name,String value){
  if (builder.length() > 1) {
    builder.append(",");
  }
  builder.append("\"").append(name).append("\":\"").append(value).append("\"");
}
