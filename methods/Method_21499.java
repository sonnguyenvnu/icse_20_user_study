private void copyMaps(Map<String,Object> into,Map<String,Object> from){
  for (  Map.Entry<String,Object> keyAndValue : from.entrySet())   into.put(keyAndValue.getKey(),keyAndValue.getValue());
}
