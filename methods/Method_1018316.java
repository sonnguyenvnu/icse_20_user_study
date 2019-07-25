/** 
 * Inspects the given handler method for an annotation of the given type. If the annotation present an {@link EventHandlerMethod} is registered for the given {@link RepositoryEvent} type.
 * @param handler must not be {@literal null}.
 * @param method must not be {@literal null}.
 * @param annotationType must not be {@literal null}.
 * @param eventType must not be {@literal null}.
 */
private <T extends Annotation>void inspect(Object handler,Method method,Class<T> annotationType,Class<? extends RepositoryEvent> eventType){
  T annotation=AnnotationUtils.findAnnotation(method,annotationType);
  if (annotation == null) {
    return;
  }
  if (method.getParameterCount() == 0) {
    throw new IllegalStateException(String.format(PARAMETER_MISSING,method));
  }
  ResolvableType parameter=ResolvableType.forMethodParameter(method,0,handler.getClass());
  EventHandlerMethod handlerMethod=EventHandlerMethod.of(parameter.resolve(),handler,method);
  if (LOG.isDebugEnabled()) {
    LOG.debug("Annotated handler method found: {}",handlerMethod);
  }
  List<EventHandlerMethod> events=handlerMethods.get(eventType);
  if (events == null) {
    events=new ArrayList<EventHandlerMethod>();
  }
  if (events.isEmpty()) {
    handlerMethods.add(eventType,handlerMethod);
    return;
  }
  events.add(handlerMethod);
  Collections.sort(events);
  handlerMethods.put(eventType,events);
}
