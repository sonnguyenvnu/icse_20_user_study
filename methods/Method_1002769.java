/** 
 * Returns a decorator chain which is specified by  {@link Decorator} annotations and user-defineddecorator annotations.
 */
private static Function<Service<HttpRequest,HttpResponse>,? extends Service<HttpRequest,HttpResponse>> decorator(Method method,Class<?> clazz,Function<Service<HttpRequest,HttpResponse>,? extends Service<HttpRequest,HttpResponse>> initialDecorator){
  final List<DecoratorAndOrder> decorators=collectDecorators(clazz,method);
  Function<Service<HttpRequest,HttpResponse>,? extends Service<HttpRequest,HttpResponse>> decorator=initialDecorator;
  for (int i=decorators.size() - 1; i >= 0; i--) {
    final DecoratorAndOrder d=decorators.get(i);
    decorator=decorator.andThen(d.decorator());
  }
  return decorator;
}
