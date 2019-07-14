public Map<String,Long> mzadds(final Map<String,Map<String,Double>> map){
  if (map == null || map.isEmpty()) {
    return null;
  }
  return new PipelineClusterCommand<Map<String,Long>>(this,connectionHandler){
    @Override public void pipelineCommand(    Pipeline pipeline,    List<String> pipelineKeys){
      for (      String key : pipelineKeys) {
        Map<String,Double> scoreMemberMap=map.get(key);
        pipeline.zadd(key,scoreMemberMap);
      }
    }
    @Override public Map<String,Long> getResult(    Map<String,Object> resultMap){
      Map<String,Long> result=new HashMap<String,Long>();
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
            Map<String,Double> voMap=map.get(key);
            Long value=pipelineCluster.zadd(key,voMap);
            result.put(key,value);
          }
 catch (          Exception e) {
            logger.error(e.getMessage(),e);
          }
        }
 else {
          result.put(key,Long.parseLong(object.toString()));
        }
      }
      return result;
    }
  }
.run(new ArrayList<String>(map.keySet()));
}
