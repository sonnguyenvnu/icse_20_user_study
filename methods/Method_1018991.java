/** 
 * Check if some input matches the content of some argument based on the current  {@link #getStringMode() string-mode}.
 * @param arg Argument to use.
 * @param input Content to check for match against arg content.
 * @param fallback Fallback return value if the arg value of the given index does not exist.
 * @return {@code true} if match.
 */
boolean check(int arg,String input,boolean fallback){
  if (arg >= args.size()) {
    return false;
  }
  Object argValue=getArg(arg);
  if (argValue == null) {
    return fallback;
  }
  String search=(String)argValue;
  if (!isCaseSensitive()) {
    search=search.toLowerCase();
    input=input.toLowerCase();
  }
switch (getStringMode()) {
case CONTAINS:
    return input.contains(search);
case ENDS_WITH:
  return input.endsWith(search);
case EQUALITY:
return input.equals(search);
case REGEX:
return Pattern.compile(search).matcher(input).find();
case STARTS_WITH:
return input.startsWith(search);
default :
return false;
}
}
