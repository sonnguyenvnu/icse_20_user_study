private void validateInterval(int timeInterval){
  if (timeInterval <= 0)   throw new IllegalArgumentException("Interval must be a positive value.");
}
