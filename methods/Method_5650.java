/** 
 * Reads and validates the first line of a WebVTT file.
 * @param input The input from which the line should be read.
 * @throws ParserException If the line isn't the start of a valid WebVTT file.
 */
public static void validateWebvttHeaderLine(ParsableByteArray input) throws ParserException {
  int startPosition=input.getPosition();
  if (!isWebvttHeaderLine(input)) {
    input.setPosition(startPosition);
    throw new ParserException("Expected WEBVTT. Got " + input.readLine());
  }
}
