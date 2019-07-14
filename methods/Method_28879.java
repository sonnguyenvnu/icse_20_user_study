public Map<String,Set<String>> mzrangeByScore(final Map<String,RangeScoreVO> keyScoreMap){
  return new PipelineClusterCommand<Map<String,Set<String>>>(this,connectionHandler){
    @Override public void pipelineCommand(    Pipeline pipeline,    List<String> pipelineKeys){
      for (      String key : pipelineKeys) {
        RangeScoreVO vo=keyScoreMap.get(key);
        pipeline.zrangeByScore(key,vo.getMin(),vo.getMax());
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
            RangeScoreVO vo=keyScoreMap.get(key);
            Set<String> value=pipelineCluster.zrangeByScore(key,vo.getMin(),vo.getMax());
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
.run(new ArrayList<String>(keyScoreMap.keySet()));
}
