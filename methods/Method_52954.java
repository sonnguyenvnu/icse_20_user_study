@Override public Map<String,List<String>> getResponseHeaderFields(){
  ensureResponseEvaluated();
  Map<String,List<String>> ret=new TreeMap<String,List<String>>();
  for (  Map.Entry<String,String> entry : headers.entrySet()) {
    ret.put(entry.getKey(),Arrays.asList(entry.getValue()));
  }
  return ret;
}
