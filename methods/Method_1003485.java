/** 
 * Creates a new  {@link ClientConfiguration} instance configured to a single host given {@link InetSocketAddress}. <p/> For example given the endpoint http://localhost:9200 <pre class="code"> ClientConfiguration configuration = ClientConfiguration .create(InetSocketAddress.createUnresolved("localhost", 9200)); </pre>
 * @return a new {@link ClientConfigurationBuilder} instance.
 */
static ClientConfiguration create(InetSocketAddress socketAddress){
  return new ClientConfigurationBuilder().connectedTo(socketAddress).build();
}
