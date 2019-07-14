private static String createSuiteDescription(TestSuite ts){
  int count=ts.countTestCases();
  String example=count == 0 ? "" : String.format(" [example: %s]",ts.testAt(0));
  return String.format("TestSuite with %s tests%s",count,example);
}
