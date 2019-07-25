/** 
 * Finds the  {@link MediaType} in this {@link List} that matches the specified media range.
 * @return the {@link MediaType} that matches the specified media range.{@link Optional#empty()} if there are no matches
 */
public Optional<MediaType> match(MediaType range){
  requireNonNull(range,"range");
  for (  MediaType candidate : mediaTypes) {
    if (candidate.belongsTo(range)) {
      return Optional.of(candidate);
    }
  }
  return Optional.empty();
}
