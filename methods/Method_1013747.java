public Map<String,String> extract(String[] keys){
  genKeyValues();
  HashMap<String,String> result=new HashMap<>();
  for (  String key : keys) {
    result.put(key,keyValues.get(key));
  }
  return result;
}
