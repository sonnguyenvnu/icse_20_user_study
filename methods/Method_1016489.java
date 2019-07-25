public static Map<String,String> table(final byte[] a){
  if (a == null)   return new ConcurrentHashMap<String,String>();
  return table(strings(a));
}
