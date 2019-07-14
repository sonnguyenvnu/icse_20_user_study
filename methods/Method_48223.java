public static Predicate<Integer> positiveInt(){
  return num -> num != null && num > 0;
}
