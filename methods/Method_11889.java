public void filter(Filter filter) throws NoTestsRemainException {
  for (Iterator<Method> iter=testMethods.iterator(); iter.hasNext(); ) {
    Method method=iter.next();
    if (!filter.shouldRun(methodDescription(method))) {
      iter.remove();
    }
  }
  if (testMethods.isEmpty()) {
    throw new NoTestsRemainException();
  }
}
