/** 
 * Returns true if the length of the string is exceeding length limitation
 * @param text String to be examined
 * @return if the length of the string is exceeding length limitation
 */
public static boolean isExceedingLengthLimitation(String text){
  return count(text) > MAX_TWEET_LENGTH;
}
