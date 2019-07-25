public static Collector<FindSeries,FindSeries> reduce(final OptionalLimit limit){
  return results -> {
    final List<RequestError> errors=new ArrayList<>();
    final ImmutableSet.Builder<Series> series=ImmutableSet.builder();
    boolean limited=false;
    for (    final FindSeries result : results) {
      errors.addAll(result.errors);
      series.addAll(result.series);
      limited|=result.limited;
    }
    final Set<Series> s=series.build();
    return new FindSeries(errors,limit.limitSet(s),limited || limit.isGreater(s.size()));
  }
;
}
