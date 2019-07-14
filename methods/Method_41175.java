public static void validateSecond(int second){
  if (second < 0 || second > 59) {
    throw new IllegalArgumentException("Invalid second (must be >= 0 and <= 59).");
  }
}
