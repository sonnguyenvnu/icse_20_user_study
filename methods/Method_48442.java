public Duration elapsed(){
  if (null == start) {
    return Duration.ZERO;
  }
  final Instant stopTime=(null == stop ? times.getTime() : stop);
  return Duration.between(start,stopTime);
}
