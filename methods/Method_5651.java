/** 
 * Parses a percentage string.
 * @param s The percentage string.
 * @return The parsed value, where 1.0 represents 100%.
 * @throws NumberFormatException If the percentage could not be parsed.
 */
public static float parsePercentage(String s) throws NumberFormatException {
  if (!s.endsWith("%")) {
    throw new NumberFormatException("Percentages must end with %");
  }
  return Float.parseFloat(s.substring(0,s.length() - 1)) / 100;
}
