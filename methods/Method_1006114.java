private static <T>Consumer<T> chain(Runnable first,Consumer<T> second){
  if (first != null) {
    if (second != null) {
      return result -> {
        first.run();
        second.accept(result);
      }
;
    }
 else {
      return result -> first.run();
    }
  }
 else {
    return second;
  }
}
