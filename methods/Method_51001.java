/** 
 * Sums the line length without the line separation and the characters which matched the line separation pattern
 * @param scanner the scanner from which to read the line's length
 * @return the length of the line with the line separator.
 */
private int getLineLengthWithLineSeparator(final Scanner scanner){
  int lineLength=scanner.nextLine().length();
  final String lineSeparationMatch=scanner.match().group(1);
  if (lineSeparationMatch != null) {
    lineLength+=lineSeparationMatch.length();
  }
  return lineLength;
}
