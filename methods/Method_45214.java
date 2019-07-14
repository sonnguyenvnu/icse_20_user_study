private static List<String> getValues(final Map<String,Iterable<String>> headers,final String key){
  if (headers.containsKey(key)) {
    return (List<String>)headers.get(key);
  }
  return new ArrayList<>();
}
