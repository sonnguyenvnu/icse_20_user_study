@Override public List<IMethodInstance> intercept(List<IMethodInstance> methods,ITestContext context){
  methods.sort(COMPARATOR);
  return methods;
}
