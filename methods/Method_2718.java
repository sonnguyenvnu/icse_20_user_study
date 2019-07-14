@SuppressWarnings("unchecked") public static Map.Entry<String,Map.Entry<String,Integer>[]> create(String param[]){
  if (param.length % 2 == 0)   return null;
  int natureCount=(param.length - 1) / 2;
  Map.Entry<String,Integer>[] entries=(Map.Entry<String,Integer>[])Array.newInstance(Map.Entry.class,natureCount);
  for (int i=0; i < natureCount; ++i) {
    entries[i]=new AbstractMap.SimpleEntry<String,Integer>(param[1 + 2 * i],Integer.parseInt(param[2 + 2 * i]));
  }
  return new AbstractMap.SimpleEntry<String,Map.Entry<String,Integer>[]>(param[0],entries);
}
