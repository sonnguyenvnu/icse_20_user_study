private void parseRequestHeader(String key,Map<String,String> headerMap,SofaRequest sofaRequest){
  Map<String,String> traceMap=new HashMap<String,String>(8);
  CodecUtils.treeCopyTo(key + ".",headerMap,traceMap,true);
  if (!traceMap.isEmpty()) {
    sofaRequest.addRequestProp(key,traceMap);
  }
}
