/** 
 * Return a consumer which rethrows possible checked exceptions as runtime exception.
 * @param consumer
 * @param < T >
 * @return
 */
public static <T>Consumer<T> consumer(CheckedConsumer<T> consumer){
  return input -> {
    try {
      consumer.accept(input);
    }
 catch (    Exception e) {
      if (e instanceof RuntimeException) {
        throw (RuntimeException)e;
      }
      throw new RuntimeException(e);
    }
  }
;
}
