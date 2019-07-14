private Class<? extends Throwable> getExpectedException(Test annotation){
  if (annotation == null || annotation.expected() == None.class) {
    return null;
  }
 else {
    return annotation.expected();
  }
}
