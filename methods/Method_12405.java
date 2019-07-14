public static Comparator<String> severity(){
  return Comparator.comparingInt(STATUS_ORDER::indexOf);
}
