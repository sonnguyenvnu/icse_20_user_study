/** 
 * Trims and removes tags from the given line. The removed tags are added to  {@code tags}.
 * @param line The line to process.
 * @param tags A list to which removed tags will be added.
 * @return The processed line.
 */
private String processLine(String line,ArrayList<String> tags){
  line=line.trim();
  int removedCharacterCount=0;
  StringBuilder processedLine=new StringBuilder(line);
  Matcher matcher=SUBRIP_TAG_PATTERN.matcher(line);
  while (matcher.find()) {
    String tag=matcher.group();
    tags.add(tag);
    int start=matcher.start() - removedCharacterCount;
    int tagLength=tag.length();
    processedLine.replace(start,start + tagLength,"");
    removedCharacterCount+=tagLength;
  }
  return processedLine.toString();
}
