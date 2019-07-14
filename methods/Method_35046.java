/** 
 * Returns a new  {@link ObjectToSourceTransformer}, which uses a  {@link JacksonBufferedSourceAdapter} to parse fromobjects of the specified type to JSON using the provided {@link com.fasterxml.jackson.databind.ObjectMapper ObjectMapper} instance.
 */
@Nonnull @Experimental public static <Parsed>ObjectToSourceTransformer<Parsed> createObjectToSourceTransformer(@Nonnull ObjectMapper objectMapper){
  return new ObjectToSourceTransformer<>(new JacksonBufferedSourceAdapter<Parsed>(objectMapper));
}
