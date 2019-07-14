private static byte[] serializeParams(HeaderParameters params){
  StringBuilder builder=new StringBuilder("{");
  appendField(builder,"alg",params.alg);
  if (params.typ != null) {
    appendField(builder,"typ",params.typ);
  }
  for (  Entry<String,String> entry : params.map.entrySet()) {
    appendField(builder,entry.getKey(),entry.getValue());
  }
  builder.append("}");
  return utf8Encode(builder.toString());
}
