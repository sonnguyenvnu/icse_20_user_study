/** 
 * Encode newline characters and launder string to mitigate log forging vulnerability.
 * @param input
 * @return
 */
public static String sanitizeAndLaunder(Object input){
  return input != null ? StringEncoding.launder(input.toString().replaceAll("[\n\r]","%0A")) : null;
}
