public static void validateMonth(int month){
  if (month < 1 || month > 12) {
    throw new IllegalArgumentException("Invalid month (must be >= 1 and <= 12.");
  }
}
