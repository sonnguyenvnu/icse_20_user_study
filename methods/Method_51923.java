/** 
 * Filters the comment by removing the leading comment marker (like  {@code *}) of each line as well as the start markers ( {@code //},  {@code /*} or {@code /**}and the end markers (<code>&#x2a;/</code>). Also leading and trailing empty lines are removed.
 * @return the filtered comment
 */
public String getFilteredComment(){
  List<String> lines=multiLinesIn();
  lines=trim(lines);
  return StringUtils.join(lines,PMD.EOL);
}
