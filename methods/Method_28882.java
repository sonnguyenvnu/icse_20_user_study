public String msetBytes(final Map<String,byte[]> keyValueMap){
  if (keyValueMap == null || keyValueMap.isEmpty()) {
    return null;
  }
  return new PipelineClusterCommand<String>(this,connectionHandler){
    @Override public void pipelineCommand(    Pipeline pipeline,    List<String> pipelineKeys){
      for (      String key : pipelineKeys) {
        byte[] value=keyValueMap.get(key);
        pipeline.set(SafeEncoder.encode(key),value);
      }
    }
    @Override public String getResult(    Map<String,Object> resultMap){
      String result="OK";
      if (resultMap == null || resultMap.isEmpty()) {
        result="FAIL";
      }
      for (      Map.Entry<String,Object> entry : resultMap.entrySet()) {
        String key=entry.getKey();
        Object object=entry.getValue();
        if (object == null) {
          continue;
        }
        if (checkException(object)) {
          try {
            byte[] value=keyValueMap.get(key);
            if (value != null) {
              pipelineCluster.set(SafeEncoder.encode(key),value);
            }
          }
 catch (          Exception e) {
            logger.error(e.getMessage(),e);
          }
        }
      }
      return result;
    }
  }
.run(new ArrayList<String>(keyValueMap.keySet()));
}
