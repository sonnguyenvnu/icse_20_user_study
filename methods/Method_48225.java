public static Predicate<Long> positiveLong(){
  return num -> num != null && num > 0;
}
