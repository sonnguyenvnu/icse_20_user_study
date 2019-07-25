private static Map<String,Integer> plus(Map<String,Integer> a,Map<String,Integer> b){
  final Map<String,Integer> result=new HashMap<>(a);
  for (  Map.Entry<String,Integer> entry : b.entrySet()) {
    final Integer num=result.get(entry.getKey());
    result.put(entry.getKey(),num != null ? num.intValue() + entry.getValue() : entry.getValue());
  }
  return result;
}
