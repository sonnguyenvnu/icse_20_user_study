public List<KeyRange> getLocalKeyPartition() throws BackendException {
  ensureKeyspaceExists(keySpaceName);
  final Collection<Range<Token>> ranges=StorageService.instance.getPrimaryRanges(keySpaceName);
  final List<KeyRange> keyRanges=new ArrayList<>(ranges.size());
  for (  Range<Token> range : ranges) {
    keyRanges.add(CassandraHelper.transformRange(range));
  }
  return keyRanges;
}
