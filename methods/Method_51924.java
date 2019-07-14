/** 
 * Removes the leading comment marker (like  {@code *}) of each line of the comment as well as the start marker ( {@code //},  {@code /*} or {@code /**}and the end markers (<code>&#x2a;/</code>).
 * @param comment the raw comment
 * @return List of lines of the comments
 */
private List<String> multiLinesIn(){
  String[] lines=NEWLINES_PATTERN.split(getImage());
  List<String> filteredLines=new ArrayList<>(lines.length);
  for (  String rawLine : lines) {
    String line=rawLine.trim();
    Matcher allMatcher=COMMENT_LINE_COMBINED.matcher(line);
    if (allMatcher.matches()) {
      filteredLines.add(allMatcher.group(1).trim());
    }
  }
  return filteredLines;
}
