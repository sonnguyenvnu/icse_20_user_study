/** 
 * Creates a new  {@link WebClientProvider} using the given {@code scheme} and a default {@link ClientHttpConnector}.
 * @param scheme protocol scheme such as {@literal http} or {@literal https}.
 * @return the resulting {@link WebClientProvider}.
 */
static WebClientProvider create(String scheme){
  Assert.hasText(scheme,"Protocol scheme must not be empty");
  return new DefaultWebClientProvider(scheme,null);
}
