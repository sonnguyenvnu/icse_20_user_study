/** 
 * Binds the specified  {@link Service} at the specified {@link Route} of the default{@link VirtualHost}.
 */
public ServerBuilder service(Route route,Service<HttpRequest,HttpResponse> service){
  defaultVirtualHostBuilder.service(route,service);
  return this;
}
