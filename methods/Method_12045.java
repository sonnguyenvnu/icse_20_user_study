private void validatePublicStaticVoidMethods(Class<? extends Annotation> annotation,Integer parameterCount,List<Throwable> errors){
  List<FrameworkMethod> methods=getTestClass().getAnnotatedMethods(annotation);
  for (  FrameworkMethod fm : methods) {
    fm.validatePublicVoid(true,errors);
    if (parameterCount != null) {
      int methodParameterCount=fm.getMethod().getParameterTypes().length;
      if (methodParameterCount != 0 && methodParameterCount != parameterCount) {
        errors.add(new Exception("Method " + fm.getName() + "() should have 0 or " + parameterCount + " parameter(s)"));
      }
    }
  }
}
