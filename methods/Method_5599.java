/** 
 * Parses a time expression, returning the parsed timestamp. <p> For the format of a time expression, see: <a href="http://www.w3.org/TR/ttaf1-dfxp/#timing-value-timeExpression">timeExpression</a>
 * @param time A string that includes the time expression.
 * @param frameAndTickRate The effective frame and tick rates of the stream.
 * @return The parsed timestamp in microseconds.
 * @throws SubtitleDecoderException If the given string does not contain a valid time expression.
 */
private static long parseTimeExpression(String time,FrameAndTickRate frameAndTickRate) throws SubtitleDecoderException {
  Matcher matcher=CLOCK_TIME.matcher(time);
  if (matcher.matches()) {
    String hours=matcher.group(1);
    double durationSeconds=Long.parseLong(hours) * 3600;
    String minutes=matcher.group(2);
    durationSeconds+=Long.parseLong(minutes) * 60;
    String seconds=matcher.group(3);
    durationSeconds+=Long.parseLong(seconds);
    String fraction=matcher.group(4);
    durationSeconds+=(fraction != null) ? Double.parseDouble(fraction) : 0;
    String frames=matcher.group(5);
    durationSeconds+=(frames != null) ? Long.parseLong(frames) / frameAndTickRate.effectiveFrameRate : 0;
    String subframes=matcher.group(6);
    durationSeconds+=(subframes != null) ? ((double)Long.parseLong(subframes)) / frameAndTickRate.subFrameRate / frameAndTickRate.effectiveFrameRate : 0;
    return (long)(durationSeconds * C.MICROS_PER_SECOND);
  }
  matcher=OFFSET_TIME.matcher(time);
  if (matcher.matches()) {
    String timeValue=matcher.group(1);
    double offsetSeconds=Double.parseDouble(timeValue);
    String unit=matcher.group(2);
switch (unit) {
case "h":
      offsetSeconds*=3600;
    break;
case "m":
  offsetSeconds*=60;
break;
case "s":
break;
case "ms":
offsetSeconds/=1000;
break;
case "f":
offsetSeconds/=frameAndTickRate.effectiveFrameRate;
break;
case "t":
offsetSeconds/=frameAndTickRate.tickRate;
break;
}
return (long)(offsetSeconds * C.MICROS_PER_SECOND);
}
throw new SubtitleDecoderException("Malformed time expression: " + time);
}
