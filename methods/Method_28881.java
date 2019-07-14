public String mzremrangeByScore(final Map<String,RangeScoreVO> keyScoreMap){
  return new PipelineClusterCommand<String>(this,connectionHandler){
    @Override public void pipelineCommand(    Pipeline pipeline,    List<String> pipelineKeys){
      for (      String key : pipelineKeys) {
        RangeScoreVO vo=keyScoreMap.get(key);
        pipeline.zremrangeByScore(key,vo.getMin(),vo.getMax());
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
            RangeScoreVO vo=keyScoreMap.get(key);
            if (vo != null) {
              pipelineCluster.zremrangeByScore(key,vo.getMin(),vo.getMax());
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
.run(new ArrayList<String>(keyScoreMap.keySet()));
}
