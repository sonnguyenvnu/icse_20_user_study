private void applyValidators(List<Throwable> errors){
  if (getTestClass().getJavaClass() != null) {
    for (    TestClassValidator each : VALIDATORS) {
      errors.addAll(each.validateTestClass(getTestClass()));
    }
  }
}
