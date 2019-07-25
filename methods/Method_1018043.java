@Override public void assess(FeedExecutedSinceSchedule metric,MetricAssessmentBuilder<Serializable> builder){
  Date prev=CronExpressionUtil.getPreviousFireTime(metric.getCronExpression(),2);
  DateTime schedTime=new DateTime(prev);
  String feedName=metric.getFeedName();
  FeedCriteria crit=getFeedProvider().feedCriteria().name(feedName);
  List<Feed> feeds=getFeedProvider().getFeeds(crit);
  if (feeds.size() > 0) {
    Feed feed=feeds.get(0);
    List<FeedOperation> list=this.getFeedOperationsProvider().findLatestCompleted(feed.getId());
    if (!list.isEmpty()) {
      FeedOperation latest=list.get(0);
      if (latest.getStopTime().isAfter(schedTime)) {
        builder.result(AssessmentResult.SUCCESS).message("Feed " + feed.getName() + " has executed at least 1 operation since " + schedTime);
      }
 else {
        builder.result(AssessmentResult.FAILURE).message("Feed " + feed.getName() + " has not executed any data operations since " + schedTime);
      }
    }
  }
}
