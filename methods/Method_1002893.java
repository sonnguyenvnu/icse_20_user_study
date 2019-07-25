/** 
 * Decorates and binds the specified  {@link ServiceWithRoutes} at multiple {@link Route}s of the default  {@link VirtualHost}.
 * @param serviceWithRoutes the {@link ServiceWithRoutes}.
 * @param decorators the decorator functions, which will be applied in the order specified.
 */
public VirtualHostBuilder service(ServiceWithRoutes<HttpRequest,HttpResponse> serviceWithRoutes,Iterable<Function<? super Service<HttpRequest,HttpResponse>,? extends Service<HttpRequest,HttpResponse>>> decorators){
  requireNonNull(serviceWithRoutes,"serviceWithRoutes");
  requireNonNull(serviceWithRoutes.routes(),"serviceWithRoutes.routes()");
  requireNonNull(decorators,"decorators");
  Service<HttpRequest,HttpResponse> decorated=serviceWithRoutes;
  for (  Function<? super Service<HttpRequest,HttpResponse>,? extends Service<HttpRequest,HttpResponse>> d : decorators) {
    checkNotNull(d,"decorators contains null: %s",decorators);
    decorated=d.apply(decorated);
    checkNotNull(decorated,"A decorator returned null: %s",d);
  }
  final Service<HttpRequest,HttpResponse> finalDecorated=decorated;
  serviceWithRoutes.routes().forEach(route -> service(route,finalDecorated));
  return this;
}
