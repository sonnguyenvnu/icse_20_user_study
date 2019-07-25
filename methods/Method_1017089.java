public static Collector<WriteSuggest,WriteSuggest> reduce(){
  return requests -> {
    final ImmutableList.Builder<RequestError> errors=ImmutableList.builder();
    final ImmutableList.Builder<Long> times=ImmutableList.builder();
    for (    final WriteSuggest r : requests) {
      errors.addAll(r.getErrors());
      times.addAll(r.getTimes());
    }
    return new WriteSuggest(errors.build(),times.build(),ImmutableList.of());
  }
;
}
