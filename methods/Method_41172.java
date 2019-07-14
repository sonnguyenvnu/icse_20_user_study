public static void validateDayOfWeek(int dayOfWeek){
  if (dayOfWeek < SUNDAY || dayOfWeek > SATURDAY) {
    throw new IllegalArgumentException("Invalid day of week.");
  }
}
