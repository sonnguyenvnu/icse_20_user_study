static Object proceed(ProceedingJoinPoint call,Tracer tracer,String spanName,String... annotations) throws Throwable {
  Scope scope=tracer.spanBuilder(spanName).startScopedSpan();
  try {
    for (    String annotation : annotations) {
      tracer.getCurrentSpan().addAnnotation(annotation);
    }
    return call.proceed();
  }
 catch (  Throwable t) {
    Map<String,AttributeValue> attributes=new HashMap<String,AttributeValue>();
    String message=t.getMessage();
    attributes.put("message",AttributeValue.stringAttributeValue(message == null ? "null" : message));
    attributes.put("type",AttributeValue.stringAttributeValue(t.getClass().toString()));
    Span span=tracer.getCurrentSpan();
    span.addAnnotation("error",attributes);
    span.setStatus(Status.UNKNOWN);
    throw t;
  }
 finally {
    scope.close();
  }
}
