public static Collector<WriteMetadata,WriteMetadata> reduce(){
  return requests -> {
    final ImmutableList.Builder<RequestError> errors=ImmutableList.builder();
    final ImmutableList.Builder<Long> times=ImmutableList.builder();
    for (    final WriteMetadata r : requests) {
      errors.addAll(r.getErrors());
      times.addAll(r.getTimes());
    }
    return new WriteMetadata(errors.build(),times.build());
  }
;
}
