@Override public void assess(DatasourceUpdatedSinceFeedExecuted metric,MetricAssessmentBuilder<Serializable> builder){
  builder.metric(metric).message("This metric is no longer supported").result(AssessmentResult.FAILURE);
}
