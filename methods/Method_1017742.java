@Override public void evaluate() throws Throwable {
  Map<String,Type> typeVariables=GenericsResolver.resolve(testClass.getJavaClass()).method(method.getMethod()).genericsMap();
  Property marker=method.getAnnotation(Property.class);
  ParameterSampler sampler=sampler(marker);
  ShrinkControl shrinkControl=new ShrinkControl(marker);
  List<PropertyParameterGenerationContext> parameters=Arrays.stream(method.getMethod().getParameters()).map(p -> parameterContextFor(p,typeVariables)).map(p -> new PropertyParameterGenerationContext(p,repo,distro,new SourceOfRandomness(new Random()),sampler)).collect(toList());
  Stream<List<SeededValue>> sample=sampler.sample(parameters);
  for (  List<SeededValue> args : (Iterable<List<SeededValue>>)sample::iterator)   property(args,shrinkControl).verify();
  if (successes == 0 && !assumptionViolations.isEmpty()) {
    throw new NoValuesSatisfiedPropertyAssumptions(assumptionViolations);
  }
}
