public Map<String,Map<String,String>> mHgetAll(List<String> keys){
  if (keys == null || keys.isEmpty()) {
    return null;
  }
  return new PipelineClusterCommand<Map<String,Map<String,String>>>(this,connectionHandler){
    @Override public void pipelineCommand(    Pipeline pipeline,    List<String> pipelineKeys){
      for (      String key : pipelineKeys) {
        pipeline.hgetAll(key);
      }
    }
    @Override public Map<String,Map<String,String>> getResult(    Map<String,Object> resultMap){
      Map<String,Map<String,String>> result=new HashMap<String,Map<String,String>>();
      for (      Map.Entry<String,Object> entry : resultMap.entrySet()) {
        String key=entry.getKey();
        Object object=entry.getValue();
        if (object == null) {
          continue;
        }
        if (checkException(object)) {
          try {
            Map<String,String> exceptionHgetAllMap=pipelineCluster.hgetAll(key);
            if (exceptionHgetAllMap != null && !exceptionHgetAllMap.isEmpty()) {
              result.put(key,exceptionHgetAllMap);
            }
          }
 catch (          Exception e) {
            logger.error(e.getMessage(),e);
          }
        }
 else {
          result.put(key,(Map<String,String>)object);
        }
      }
      return result;
    }
  }
.run(keys);
}
