public ResultLimits add(final ResultLimit limit){
  return new ResultLimits(ImmutableSet.<ResultLimit>builder().add(limit).addAll(limits).build());
}
