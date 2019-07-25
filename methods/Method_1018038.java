@Override public void assess(DatasourceUpdatedSinceSchedule metric,MetricAssessmentBuilder<Serializable> builder){
  builder.metric(metric).message("This metric is no longer supported").result(AssessmentResult.FAILURE);
}
