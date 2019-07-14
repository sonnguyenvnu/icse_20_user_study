/** 
 * ??????????????
 * @param inputed
 * @return
 */
public static boolean isNumberOrAlpha(String inputed){
  if (inputed == null) {
    Log.e(TAG,"isNumberOrAlpha  inputed == null >> return false;");
    return false;
  }
  Pattern pNumber=Pattern.compile("[0-9]*");
  Matcher mNumber;
  Pattern pAlpha=Pattern.compile("[a-zA-Z]");
  Matcher mAlpha;
  for (int i=0; i < inputed.length(); i++) {
    mNumber=pNumber.matcher(inputed.substring(i,i + 1));
    mAlpha=pAlpha.matcher(inputed.substring(i,i + 1));
    if (!mNumber.matches() && !mAlpha.matches()) {
      return false;
    }
  }
  currentString=inputed;
  return true;
}
