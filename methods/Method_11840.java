@Override protected List<FrameworkMethod> computeTestMethods(){
  List<FrameworkMethod> testMethods=new ArrayList<FrameworkMethod>(super.computeTestMethods());
  List<FrameworkMethod> theoryMethods=getTestClass().getAnnotatedMethods(Theory.class);
  testMethods.removeAll(theoryMethods);
  testMethods.addAll(theoryMethods);
  return testMethods;
}
