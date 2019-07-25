public MetricResponse metric(String requiredMetricName,@Nullable List<String> tag){
  List<Tag> tags=parseTags(tag);
  Collection<Meter> meters=findFirstMatchingMeters(this.registry,requiredMetricName,tags);
  if (meters.isEmpty()) {
    return null;
  }
  Map<Statistic,Double> samples=getSamples(meters);
  Map<String,Set<String>> availableTags=getAvailableTags(meters);
  tags.forEach((t) -> availableTags.remove(t.getKey()));
  Meter.Id meterId=meters.iterator().next().getId();
  return new MetricResponse(requiredMetricName,meterId.getDescription(),meterId.getBaseUnit(),asList(samples,Sample::new),asList(availableTags,AvailableTag::new));
}
