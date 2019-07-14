/** 
 * Converts all tabs on a line to spaces according to the provided tab width. This is not a simple tab to spaces replacement, since the resulting indentation remains the same.
 */
public static String convertTabsToSpaces(final String line,final int tabWidth){
  int tab_index, tab_size;
  int last_tab_index=0;
  int added_chars=0;
  if (tabWidth == 0) {
    return StringUtil.remove(line,'\t');
  }
  StringBuilder result=new StringBuilder();
  while ((tab_index=line.indexOf('\t',last_tab_index)) != -1) {
    tab_size=tabWidth - ((tab_index + added_chars) % tabWidth);
    if (tab_size == 0) {
      tab_size=tabWidth;
    }
    added_chars+=tab_size - 1;
    result.append(line,last_tab_index,tab_index);
    result.append(StringUtil.repeat(' ',tab_size));
    last_tab_index=tab_index + 1;
  }
  if (last_tab_index == 0) {
    return line;
  }
  result.append(line.substring(last_tab_index));
  return result.toString();
}
