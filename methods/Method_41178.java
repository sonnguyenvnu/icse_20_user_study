public static void validateYear(int year){
  if (year < 0 || year > MAX_YEAR) {
    throw new IllegalArgumentException("Invalid year (must be >= 0 and <= " + MAX_YEAR);
  }
}
