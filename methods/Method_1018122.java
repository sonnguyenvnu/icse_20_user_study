@Override public void assess(FeedFailedMetric metric,MetricAssessmentBuilder<Serializable> builder){
  builder.metric(metric);
  String feedName=metric.getFeedName();
  HashMap<String,String> data=new HashMap<>();
  data.put("feed",feedName);
  FeedFailureService.LastFeedJob lastFeedJob=feedFailureService.findLatestJob(feedName);
  DateTime lastTime=lastFeedJob.getDateTime();
  data.put("dateTime",lastTime.toString());
  data.put("dateTimeMillis",lastTime.getMillis() + "");
  LOG.debug("Assessing FeedFailureMetric for '{}'.  The Last Feed Job was: {} ",feedName,lastFeedJob);
  if (!feedFailureService.isEmptyJob(lastFeedJob)) {
    if (lastFeedJob.getBatchJobExecutionId() != null) {
      data.put("jobExecutionId",lastFeedJob.getBatchJobExecutionId().toString());
    }
    builder.compareWith(feedName,lastTime.getMillis());
    if (lastFeedJob.isFailure()) {
      data.put("status","FAILURE");
      String message="Feed " + feedName + " has failed on " + lastFeedJob.getDateTime();
      if (lastFeedJob.getBatchJobExecutionId() != null) {
        message+=". Batch Job ExecutionId: " + lastFeedJob.getBatchJobExecutionId();
      }
      LOG.debug(message);
      builder.message(message).data(data).result(AssessmentResult.FAILURE);
    }
 else {
      data.put("status","SUCCESS");
      String message="Feed " + feedName + " has succeeded on " + lastFeedJob.getDateTime();
      if (lastFeedJob.getBatchJobExecutionId() != null) {
        message+=". Batch Job ExecutionId: " + lastFeedJob.getBatchJobExecutionId();
      }
      LOG.debug(message);
      builder.message(message).data(data).result(AssessmentResult.SUCCESS);
    }
  }
 else {
    LOG.debug("FeedFailureMetric found an no recent jobs for '{}'. Returning SUCCESS ",feedName);
    builder.data(data).message("No Jobs found for feed " + feedName + " since " + lastFeedJob.getDateTime()).result(AssessmentResult.SUCCESS);
  }
}
