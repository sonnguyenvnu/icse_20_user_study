/** 
 * Formats input to plain mm:ss format
 * @param timerInSeconds duration in seconds
 * @return time in mm:ss format
 */
public static String formatTimer(long timerInSeconds){
  final long min=TimeUnit.SECONDS.toMinutes(timerInSeconds);
  final long sec=TimeUnit.SECONDS.toSeconds(timerInSeconds - TimeUnit.MINUTES.toSeconds(min));
  return String.format("%02d:%02d",min,sec);
}
