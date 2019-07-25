@Override public MeterIdPrefix apply(MeterRegistry registry,RequestLog log){
  final ImmutableList.Builder<Tag> tagListBuilder=ImmutableList.builderWithExpectedSize(3);
  buildTags(tagListBuilder,log);
  RequestMetricSupport.appendHttpStatusTag(tagListBuilder,log);
  return new MeterIdPrefix(name,tagListBuilder.build());
}
