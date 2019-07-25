@Override public String format(String fieldText){
  String result=fieldText;
  for (  Pattern key : ligaturesMap.keySet()) {
    result=key.matcher(result).replaceAll(ligaturesMap.get(key));
  }
  return result;
}
