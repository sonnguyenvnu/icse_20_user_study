public static ConcurrentHashMap<String,String> table(final Reader r){
  final BufferedReader br=new BufferedReader(r);
  return table(new StringsIterator(br));
}
