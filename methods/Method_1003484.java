/** 
 * Creates a new  {@link ClientConfiguration} instance configured to a single host given {@code hostAndPort}. <p/> For example given the endpoint http://localhost:9200 <pre class="code"> ClientConfiguration configuration = ClientConfiguration.create("localhost:9200"); </pre>
 * @return a new {@link ClientConfigurationBuilder} instance.
 */
static ClientConfiguration create(String hostAndPort){
  return new ClientConfigurationBuilder().connectedTo(hostAndPort).build();
}
