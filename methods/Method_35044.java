/** 
 * Returns a new Parser which parses from a String to the specified type, using the provided  {@link JsonFactory} instance.
 */
@Nonnull @SuppressWarnings("PMD.AvoidThrowingNullPointerException") public static <T>Parser<String,T> createStringParser(@Nonnull JsonFactory jsonFactory,@Nonnull Type type){
  if (jsonFactory == null) {
    throw new NullPointerException("jsonFactory cannot be null.");
  }
  if (type == null) {
    throw new NullPointerException("type cannot be null.");
  }
  return new JacksonStringParser<>(jsonFactory,type);
}
