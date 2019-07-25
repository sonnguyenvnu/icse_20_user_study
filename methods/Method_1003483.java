/** 
 * Creates a new  {@link ClientConfiguration} instance configured to localhost.<p/> <pre class="code"> // "localhost:9200" ClientConfiguration configuration = ClientConfiguration.localhost(); </pre>
 * @return a new {@link ClientConfiguration} instance
 * @see ClientConfigurationBuilder#connectedToLocalhost()
 */
static ClientConfiguration localhost(){
  return new ClientConfigurationBuilder().connectedToLocalhost().build();
}
