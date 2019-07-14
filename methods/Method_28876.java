public Long mdel(final List<String> keys){
  if (keys == null || keys.isEmpty()) {
    return null;
  }
  return new PipelineClusterCommand<Long>(this,connectionHandler){
    @Override public void pipelineCommand(    Pipeline pipeline,    List<String> pipelineKeys){
      for (      String key : pipelineKeys) {
        pipeline.del(key);
      }
    }
    @Override public Long getResult(    Map<String,Object> resultMap){
      Long result=0L;
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
            Long value=pipelineCluster.del(key);
            if (value != null) {
              result++;
            }
          }
 catch (          Exception e) {
            logger.error(e.getMessage(),e);
          }
        }
 else {
          result++;
        }
      }
      return result;
    }
  }
.run(keys);
}
