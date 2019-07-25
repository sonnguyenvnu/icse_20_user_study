public Summary end(){
  final Histogram.Builder tagsSize=Histogram.builder();
  final Histogram.Builder resourceSize=Histogram.builder();
  for (  final Map.Entry<String,Collection<String>> e : this.tags.asMap().entrySet()) {
    tagsSize.add(e.getValue().size());
  }
  for (  final Map.Entry<String,Collection<String>> e : this.resource.asMap().entrySet()) {
    resourceSize.add(e.getValue().size());
  }
  return new Summary(uniqueKeys.size(),tagsSize.build(),resourceSize.build(),seriesSize.build());
}
