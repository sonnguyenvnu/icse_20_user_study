protected List<ClusteredEntry> getClusteredEntries(Set<Serializable> s){
  return s.stream().map(e -> new ClusteredEntry(e,_counts.get(e))).sorted(ClusteredEntry.comparator).collect(Collectors.toList());
}
