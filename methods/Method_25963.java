private static RetentionPolicy effectiveRetentionPolicy(Element element){
  RetentionPolicy retentionPolicy=RetentionPolicy.CLASS;
  Retention retentionAnnotation=element.getAnnotation(Retention.class);
  if (retentionAnnotation != null) {
    retentionPolicy=retentionAnnotation.value();
  }
  return retentionPolicy;
}
