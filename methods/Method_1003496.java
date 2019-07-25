/** 
 * Creates a new  {@link WebClientProvider} given {@code scheme} and {@link ClientHttpConnector}.
 * @param scheme protocol scheme such as {@literal http} or {@literal https}.
 * @param connector the HTTP connector to use. Can be {@literal null}.
 * @return the resulting {@link WebClientProvider}.
 */
static WebClientProvider create(String scheme,@Nullable ClientHttpConnector connector){
  Assert.hasText(scheme,"Protocol scheme must not be empty");
  return new DefaultWebClientProvider(scheme,connector);
}
