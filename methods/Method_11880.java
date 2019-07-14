public void filter(Filter filter) throws NoTestsRemainException {
  if (getTest() instanceof Filterable) {
    Filterable adapter=(Filterable)getTest();
    adapter.filter(filter);
  }
 else   if (getTest() instanceof TestSuite) {
    TestSuite suite=(TestSuite)getTest();
    TestSuite filtered=new TestSuite(suite.getName());
    int n=suite.testCount();
    for (int i=0; i < n; i++) {
      Test test=suite.testAt(i);
      if (filter.shouldRun(makeDescription(test))) {
        filtered.addTest(test);
      }
    }
    setTest(filtered);
    if (filtered.testCount() == 0) {
      throw new NoTestsRemainException();
    }
  }
}
