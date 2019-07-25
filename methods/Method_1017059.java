public static Collector<FindSeriesIds,FindSeriesIds> reduce(final OptionalLimit limit){
  return results -> {
    final List<RequestError> errors=new ArrayList<>();
    final ImmutableSet.Builder<String> ids=ImmutableSet.builder();
    boolean limited=false;
    for (    final FindSeriesIds result : results) {
      errors.addAll(result.errors);
      ids.addAll(result.ids);
      limited|=result.limited;
    }
    final Set<String> s=ids.build();
    return new FindSeriesIds(errors,limit.limitSet(s),limited || limit.isGreater(s.size()));
  }
;
}
