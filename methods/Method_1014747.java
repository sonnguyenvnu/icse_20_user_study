public static <T>T decode(final byte[] data,Class<T> classOfT){
  final String json=new String(data,CHARSET_UTF8);
  return fromJson(json,classOfT);
}
