/** 
 * Binds the specified  {@link Service} at the specified {@link Route}.
 */
public VirtualHostBuilder service(Route route,Service<HttpRequest,HttpResponse> service){
  serviceConfigBuilders.add(new ServiceConfigBuilder(route,service));
  return this;
}
