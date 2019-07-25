protected static void convert(Map<String,Map<String,Object>> in,Map<String,String> out){
  if (in != null)   in.entrySet().stream().filter(e -> e.getValue() != null).forEach(e -> out.put(e.getKey(),e.getValue().toString()));
}
