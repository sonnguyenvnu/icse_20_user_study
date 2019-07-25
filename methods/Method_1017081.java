public static Collector<WriteMetric,WriteMetric> reduce(){
  return results -> {
    final ImmutableList.Builder<RequestError> errors=ImmutableList.builder();
    final ImmutableList.Builder<Long> times=ImmutableList.builder();
    for (    final WriteMetric r : results) {
      errors.addAll(r.getErrors());
      times.addAll(r.getTimes());
    }
    return new WriteMetric(errors.build(),times.build());
  }
;
}
