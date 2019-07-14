/** 
 * Parses an SSA timecode string.
 * @param timeString The string to parse.
 * @return The parsed timestamp in microseconds.
 */
public static long parseTimecodeUs(String timeString){
  Matcher matcher=SSA_TIMECODE_PATTERN.matcher(timeString);
  if (!matcher.matches()) {
    return C.TIME_UNSET;
  }
  long timestampUs=Long.parseLong(matcher.group(1)) * 60 * 60 * C.MICROS_PER_SECOND;
  timestampUs+=Long.parseLong(matcher.group(2)) * 60 * C.MICROS_PER_SECOND;
  timestampUs+=Long.parseLong(matcher.group(3)) * C.MICROS_PER_SECOND;
  timestampUs+=Long.parseLong(matcher.group(4)) * 10000;
  return timestampUs;
}
