/** 
 * Returns a new Parser which parses from  {@link BufferedSource} to the specified type, usingthe provided  {@link JsonFactory} instance.
 */
@Nonnull @SuppressWarnings("PMD.AvoidThrowingNullPointerException") public static <T>Parser<BufferedSource,T> createSourceParser(@Nonnull JsonFactory jsonFactory,@Nonnull Type type){
  if (jsonFactory == null) {
    throw new NullPointerException("jsonFactory cannot be null.");
  }
  if (type == null) {
    throw new NullPointerException("type cannot be null.");
  }
  return new JacksonSourceParser<>(jsonFactory,type);
}
