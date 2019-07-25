public static Collector<Ingestion,Ingestion> reduce(){
  return results -> {
    final ImmutableList.Builder<RequestError> errors=ImmutableList.builder();
    final ImmutableList.Builder<Long> times=ImmutableList.builder();
    for (    final Ingestion r : results) {
      errors.addAll(r.errors);
      times.addAll(r.times);
    }
    return new Ingestion(errors.build(),times.build());
  }
;
}
