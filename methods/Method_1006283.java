public static <T,U>boolean equals(Optional<T> left,Optional<U> right,BiPredicate<T,U> equality){
  if (!left.isPresent()) {
    return !right.isPresent();
  }
 else {
    if (right.isPresent()) {
      return equality.test(left.get(),right.get());
    }
 else {
      return false;
    }
  }
}
