public static Predicate<Integer> nonnegativeInt(){
  return num -> num != null && num >= 0;
}
