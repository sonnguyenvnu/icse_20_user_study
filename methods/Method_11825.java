@Override protected Collection<FrameworkMethod> getSingleDataPointMethods(ParameterSignature sig){
  Collection<FrameworkMethod> methods=super.getSingleDataPointMethods(sig);
  String requestedName=sig.getAnnotation(FromDataPoints.class).value();
  List<FrameworkMethod> methodsWithMatchingNames=new ArrayList<FrameworkMethod>();
  for (  FrameworkMethod method : methods) {
    String[] methodNames=method.getAnnotation(DataPoint.class).value();
    if (Arrays.asList(methodNames).contains(requestedName)) {
      methodsWithMatchingNames.add(method);
    }
  }
  return methodsWithMatchingNames;
}
