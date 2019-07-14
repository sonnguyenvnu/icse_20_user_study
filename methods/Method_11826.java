@Override protected Collection<FrameworkMethod> getDataPointsMethods(ParameterSignature sig){
  Collection<FrameworkMethod> methods=super.getDataPointsMethods(sig);
  String requestedName=sig.getAnnotation(FromDataPoints.class).value();
  List<FrameworkMethod> methodsWithMatchingNames=new ArrayList<FrameworkMethod>();
  for (  FrameworkMethod method : methods) {
    String[] methodNames=method.getAnnotation(DataPoints.class).value();
    if (Arrays.asList(methodNames).contains(requestedName)) {
      methodsWithMatchingNames.add(method);
    }
  }
  return methodsWithMatchingNames;
}
