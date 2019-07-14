/** 
 * Removes starting and ending single or double quotes.
 */
public static String removeQuotes(final String string){
  if ((startsWithChar(string,'\'') && endsWithChar(string,'\'')) || (startsWithChar(string,'"') && endsWithChar(string,'"')) || (startsWithChar(string,'`') && endsWithChar(string,'`'))) {
    return substring(string,1,-1);
  }
  return string;
}
