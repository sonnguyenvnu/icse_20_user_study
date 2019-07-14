private static Map<String,Iterable<String>> toHeaders(final Iterable<Map.Entry<String,String>> httpHeaders){
  Map<String,Iterable<String>> headers=new HashMap<>();
  for (  Map.Entry<String,String> entry : httpHeaders) {
    String key=entry.getKey();
    List<String> values=getValues(headers,key);
    values.add(entry.getValue());
    headers.put(key,values);
  }
  return headers;
}
