private void validate() throws InitializationError {
  List<Throwable> errors=new ArrayList<Throwable>();
  collectInitializationErrors(errors);
  if (!errors.isEmpty()) {
    throw new InvalidTestClassError(testClass.getJavaClass(),errors);
  }
}
