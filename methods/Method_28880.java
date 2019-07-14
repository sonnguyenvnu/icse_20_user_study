public Map<String,Set<String>> mzrangeByScore(final List<String> keys,final String min,final String max){
  return new PipelineClusterCommand<Map<String,Set<String>>>(this,connectionHandler){
    @Override public void pipelineCommand(    Pipeline pipeline,    List<String> pipelineKeys){
      for (      String key : pipelineKeys) {
        pipeline.zrangeByScore(key,min,max);
      }
    }
    @Override public Map<String,Set<String>> getResult(    Map<String,Object> resultMap){
      Map<String,Set<String>> result=new HashMap<String,Set<String>>();
      if (resultMap == null || resultMap.isEmpty()) {
        return result;
      }
      for (      Map.Entry<String,Object> entry : resultMap.entrySet()) {
        String key=entry.getKey();
        Object object=entry.getValue();
        if (object == null) {
          continue;
        }
        if (checkException(object)) {
          try {
            Set<String> value=pipelineCluster.zrangeByScore(key,min,max);
            result.put(key,value);
          }
 catch (          Exception e) {
            logger.error(e.getMessage(),e);
          }
        }
 else {
          result.put(key,(Set<String>)object);
        }
      }
      return result;
    }
  }
.run(keys);
}
