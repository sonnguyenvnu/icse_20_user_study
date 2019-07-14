private void addMultiPointMethods(ParameterSignature sig,List<PotentialAssignment> list) throws Throwable {
  for (  FrameworkMethod dataPointsMethod : getDataPointsMethods(sig)) {
    Class<?> returnType=dataPointsMethod.getReturnType();
    if ((returnType.isArray() && sig.canPotentiallyAcceptType(returnType.getComponentType())) || Iterable.class.isAssignableFrom(returnType)) {
      try {
        addDataPointsValues(returnType,sig,dataPointsMethod.getName(),list,dataPointsMethod.invokeExplosively(null));
      }
 catch (      Throwable throwable) {
        DataPoints annotation=dataPointsMethod.getAnnotation(DataPoints.class);
        if (annotation != null && isAssignableToAnyOf(annotation.ignoredExceptions(),throwable)) {
          return;
        }
 else {
          throw throwable;
        }
      }
    }
  }
}
