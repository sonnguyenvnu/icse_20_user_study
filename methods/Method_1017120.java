public IngestionComponent module(PrimaryComponent primary,SuggestComponent suggest,MetadataComponent metadata,MetricComponent metric){
  return DaggerIngestionModule_C.builder().primaryComponent(primary).suggestComponent(suggest).metadataComponent(metadata).metricComponent(metric).m(new M()).build();
}
