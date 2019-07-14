protected List<InternalVertex> allRequiredRepresentatives(InternalVertex partitionedVertex){
  if (hasAllCanonicalTypes()) {
    return ImmutableList.of(tx.getCanonicalVertex(partitionedVertex));
  }
  return Arrays.asList(tx.getAllRepresentatives(partitionedVertex,restrict2Partitions));
}
