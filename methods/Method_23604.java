private void checkMinMax(String functionName){
  if (count == 0) {
    String msg=String.format("Cannot use %s() on an empty %s.",functionName,getClass().getSimpleName());
    throw new RuntimeException(msg);
  }
}
