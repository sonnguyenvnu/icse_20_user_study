/** 
 * Returns the list of  {@link AnnotatedHttpService} defined by {@link Path} and HTTP method annotationsfrom the specified  {@code object}.
 */
public static List<AnnotatedHttpServiceElement> find(String pathPrefix,Object object,Iterable<?> exceptionHandlersAndConverters){
  Builder<ExceptionHandlerFunction> exceptionHandlers=null;
  Builder<RequestConverterFunction> requestConverters=null;
  Builder<ResponseConverterFunction> responseConverters=null;
  for (  final Object o : exceptionHandlersAndConverters) {
    boolean added=false;
    if (o instanceof ExceptionHandlerFunction) {
      if (exceptionHandlers == null) {
        exceptionHandlers=ImmutableList.builder();
      }
      exceptionHandlers.add((ExceptionHandlerFunction)o);
      added=true;
    }
    if (o instanceof RequestConverterFunction) {
      if (requestConverters == null) {
        requestConverters=ImmutableList.builder();
      }
      requestConverters.add((RequestConverterFunction)o);
      added=true;
    }
    if (o instanceof ResponseConverterFunction) {
      if (responseConverters == null) {
        responseConverters=ImmutableList.builder();
      }
      responseConverters.add((ResponseConverterFunction)o);
      added=true;
    }
    if (!added) {
      throw new IllegalArgumentException(o.getClass().getName() + " is neither an exception handler nor a converter.");
    }
  }
  final List<ExceptionHandlerFunction> exceptionHandlerFunctions=exceptionHandlers != null ? exceptionHandlers.build() : ImmutableList.of();
  final List<RequestConverterFunction> requestConverterFunctions=requestConverters != null ? requestConverters.build() : ImmutableList.of();
  final List<ResponseConverterFunction> responseConverterFunctions=responseConverters != null ? responseConverters.build() : ImmutableList.of();
  final List<Method> methods=requestMappingMethods(object);
  return methods.stream().map((  Method method) -> create(pathPrefix,object,method,exceptionHandlerFunctions,requestConverterFunctions,responseConverterFunctions)).collect(toImmutableList());
}
