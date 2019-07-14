/** 
 * Reads lines up to and including the next WebVTT cue header.
 * @param input The input from which lines should be read.
 * @return A {@link Matcher} for the WebVTT cue header, or null if the end of the input wasreached without a cue header being found. In the case that a cue header is found, groups 1, 2 and 3 of the returned matcher contain the start time, end time and settings list.
 */
public static Matcher findNextCueHeader(ParsableByteArray input){
  String line;
  while ((line=input.readLine()) != null) {
    if (COMMENT.matcher(line).matches()) {
      while ((line=input.readLine()) != null && !line.isEmpty()) {
      }
    }
 else {
      Matcher cueHeaderMatcher=WebvttCueParser.CUE_HEADER_PATTERN.matcher(line);
      if (cueHeaderMatcher.matches()) {
        return cueHeaderMatcher;
      }
    }
  }
  return null;
}
