/** 
 * Returns the specified millisecond time formatted as a string.
 * @param builder The builder that {@code formatter} will write to.
 * @param formatter The formatter.
 * @param timeMs The time to format as a string, in milliseconds.
 * @return The time formatted as a string.
 */
public static String getStringForTime(StringBuilder builder,Formatter formatter,long timeMs){
  if (timeMs == C.TIME_UNSET) {
    timeMs=0;
  }
  long totalSeconds=(timeMs + 500) / 1000;
  long seconds=totalSeconds % 60;
  long minutes=(totalSeconds / 60) % 60;
  long hours=totalSeconds / 3600;
  builder.setLength(0);
  return hours > 0 ? formatter.format("%d:%02d:%02d",hours,minutes,seconds).toString() : formatter.format("%02d:%02d",minutes,seconds).toString();
}
