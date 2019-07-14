private void addSinglePointMethods(ParameterSignature sig,List<PotentialAssignment> list){
  for (  FrameworkMethod dataPointMethod : getSingleDataPointMethods(sig)) {
    if (sig.canAcceptType(dataPointMethod.getType())) {
      list.add(new MethodParameterValue(dataPointMethod));
    }
  }
}
