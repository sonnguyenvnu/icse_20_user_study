public static void validateDayOfMonth(int day){
  if (day < 1 || day > 31) {
    throw new IllegalArgumentException("Invalid day of month.");
  }
}
