/** 
 * Decorates and binds the specified  {@link ServiceWithRoutes} at multiple {@link Route}s of the default  {@link VirtualHost}.
 * @param serviceWithRoutes the {@link ServiceWithRoutes}.
 * @param decorators the decorator functions, which will be applied in the order specified.
 */
@SafeVarargs public final ServerBuilder service(ServiceWithRoutes<HttpRequest,HttpResponse> serviceWithRoutes,Function<? super Service<HttpRequest,HttpResponse>,? extends Service<HttpRequest,HttpResponse>>... decorators){
  defaultVirtualHostBuilder.service(serviceWithRoutes,decorators);
  return this;
}
