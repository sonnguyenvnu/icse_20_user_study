@JsonValue public List<List<ClusteredEntry>> getJsonRepresentation(){
  return _clusters.stream().filter(m -> m.size() > 1).map(m -> getClusteredEntries(m)).collect(Collectors.toList());
}
