private static ImmutableListMultimap<String,ChronoField> buildUnsupported(){
  ImmutableListMultimap.Builder<String,ChronoField> builder=ImmutableListMultimap.builder();
  for (  TemporalAccessor temporalAccessor : TEMPORAL_ACCESSOR_INSTANCES) {
    for (    ChronoField chronoField : ChronoField.values()) {
      if (!temporalAccessor.isSupported(chronoField)) {
        builder.put(temporalAccessor.getClass().getCanonicalName(),chronoField);
      }
    }
  }
  return builder.build();
}
