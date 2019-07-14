static <T>T unCheck(UnCheck<T> unCheck){
  try {
    return unCheck.call();
  }
 catch (  Exception e) {
    throw new UnCheckException(e);
  }
}
