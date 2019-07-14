protected void validateNoNonStaticInnerClass(List<Throwable> errors){
  if (getTestClass().isANonStaticInnerClass()) {
    String gripe="The inner class " + getTestClass().getName() + " is not static.";
    errors.add(new Exception(gripe));
  }
}
