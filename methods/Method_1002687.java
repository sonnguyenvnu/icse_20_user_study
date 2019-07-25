/** 
 * Parses the specified  {@code "cache-control"} header values into a {@link ClientCacheControl}. Note that any unknown directives will be ignored.
 * @return the {@link ClientCacheControl} decoded from the specified header values.
 */
public static ClientCacheControl parse(String... directives){
  return parse(ImmutableList.copyOf(requireNonNull(directives,"directives")));
}
