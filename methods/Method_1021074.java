public static <T>Try<T> cons(final Callable<T> func){
  try {
    return Try.success(func.call());
  }
 catch (  Exception e) {
    return Try.failure(e);
  }
}
