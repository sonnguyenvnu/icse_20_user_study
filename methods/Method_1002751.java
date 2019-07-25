/** 
 * Finds the  {@link SerializationFormat} which is accepted by any of the specified media ranges.
 */
public static Optional<SerializationFormat> find(MediaType... ranges){
  requireNonNull(ranges,"ranges");
  if (ranges.length == 0) {
    return Optional.empty();
  }
  for (  SerializationFormat f : values()) {
    if (f.isAccepted(Arrays.asList(ranges))) {
      return Optional.of(f);
    }
  }
  return Optional.empty();
}
