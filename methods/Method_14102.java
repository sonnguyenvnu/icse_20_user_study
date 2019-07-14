@JsonValue public List<List<ClusteredEntry>> getJsonRepresentation(){
  EntriesComparator c=new EntriesComparator();
  return _clusters.stream().map(m -> m.entrySet().stream().sorted(c).map(e -> new ClusteredEntry(e.getKey(),e.getValue())).collect(Collectors.toList())).collect(Collectors.toList());
}
