public static void validateHour(int hour){
  if (hour < 0 || hour > 23) {
    throw new IllegalArgumentException("Invalid hour (must be >= 0 and <= 23).");
  }
}
