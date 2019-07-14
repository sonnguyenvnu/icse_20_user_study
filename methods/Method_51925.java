/** 
 * Similar to the String.trim() function, this one removes the leading and trailing empty/blank lines from the line list.
 * @param lines the list of lines, which might contain empty lines
 * @return the lines without leading or trailing blank lines.
 */
static List<String> trim(List<String> lines){
  if (lines == null) {
    return Collections.emptyList();
  }
  List<String> result=new ArrayList<>(lines.size());
  List<String> tempList=new ArrayList<>();
  boolean foundFirstNonEmptyLine=false;
  for (  String line : lines) {
    if (StringUtils.isNotBlank(line)) {
      result.addAll(tempList);
      tempList.clear();
      result.add(line);
      foundFirstNonEmptyLine=true;
    }
 else {
      if (foundFirstNonEmptyLine) {
        tempList.add(line);
      }
    }
  }
  return result;
}
