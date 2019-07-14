@Override public void onFunctionResponse(String type) throws Exception {
  Map<String,String> map=functionMap == null ? null : functionMap.get(type);
  Set<Entry<String,String>> functionSet=map == null ? null : map.entrySet();
  if (functionSet != null && functionSet.isEmpty() == false) {
    for (    Entry<String,String> entry : functionSet) {
      parseFunction(response,entry.getKey(),entry.getValue());
    }
  }
}
