/** 
 * Create a new  {@link DefaultReactiveElasticsearchClient} given {@link ClientConfiguration}. <br /> <strong>NOTE</strong> If the cluster requires authentication be sure to provide the according  {@link HttpHeaders}correctly.
 * @param clientConfiguration Client configuration. Must not be {@literal null}.
 * @return new instance of {@link DefaultReactiveElasticsearchClient}.
 */
public static ReactiveElasticsearchClient create(ClientConfiguration clientConfiguration){
  Assert.notNull(clientConfiguration,"ClientConfiguration must not be null");
  WebClientProvider provider=getWebClientProvider(clientConfiguration);
  HostProvider hostProvider=HostProvider.provider(provider,clientConfiguration.getEndpoints().toArray(new InetSocketAddress[0]));
  return new DefaultReactiveElasticsearchClient(hostProvider);
}
