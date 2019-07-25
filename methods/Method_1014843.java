@SuppressWarnings("unchecked") private void process(Object input,WalkedPath walkedPath){
  if (input instanceof Map) {
    Set<Map.Entry<String,Object>> entrySet=new HashSet<>(((Map<String,Object>)input).entrySet());
    for (    Map.Entry<String,Object> inputEntry : entrySet) {
      applyKeyToLiteralAndComputed(this,inputEntry.getKey(),inputEntry.getValue(),walkedPath,input);
    }
  }
 else   if (input instanceof List) {
    for (int index=0; index < ((List<Object>)input).size(); index++) {
      Object subInput=((List<Object>)input).get(index);
      String subKeyStr=Integer.toString(index);
      applyKeyToLiteralAndComputed(this,subKeyStr,subInput,walkedPath,input);
    }
  }
 else   if (input != null) {
    String scalarInput=input.toString();
    applyKeyToLiteralAndComputed(this,scalarInput,null,walkedPath,scalarInput);
  }
}
