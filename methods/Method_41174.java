public static void validateMinute(int minute){
  if (minute < 0 || minute > 59) {
    throw new IllegalArgumentException("Invalid minute (must be >= 0 and <= 59).");
  }
}
