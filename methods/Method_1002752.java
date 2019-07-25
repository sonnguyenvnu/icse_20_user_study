/** 
 * Parses the specified  {@code "cache-control"} header values into a {@link ServerCacheControl}. Note that any unknown directives will be ignored.
 * @return the {@link ServerCacheControl} decoded from the specified header values.
 */
public static ServerCacheControl parse(String... directives){
  return parse(ImmutableList.copyOf(requireNonNull(directives,"directives")));
}
