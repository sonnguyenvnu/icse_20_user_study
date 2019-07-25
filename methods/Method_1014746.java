public static byte[] encode(final Object obj){
  final String json=toJson(obj,false);
  if (json != null) {
    return json.getBytes(CHARSET_UTF8);
  }
  return null;
}
