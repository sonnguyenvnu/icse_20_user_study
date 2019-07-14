/** 
 * Escape constant fields into regular expression
 * @param regex   The destination regex
 * @param value   The source field
 * @param unquote If true, replace two success quotes ('') with single quote (')
 * @return The <code>StringBuilder</code>
 */
private static StringBuilder escapeRegex(final StringBuilder regex,final String value,final boolean unquote){
  regex.append("\\Q");
  for (int i=0; i < value.length(); ++i) {
    char c=value.charAt(i);
switch (c) {
case '\'':
      if (unquote) {
        if (++i == value.length()) {
          return regex;
        }
        c=value.charAt(i);
      }
    break;
case '\\':
  if (++i == value.length()) {
    break;
  }
regex.append(c);
c=value.charAt(i);
if (c == 'E') {
regex.append("E\\\\E\\");
c='Q';
}
break;
default :
break;
}
regex.append(c);
}
regex.append("\\E");
return regex;
}
