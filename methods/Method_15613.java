/** 
 * ?????????????
 * @param number
 * @return
 */
public static boolean isIDCard(String number){
  if (isNumberOrAlpha(number) == false) {
    return false;
  }
  number=getString(number);
  if (number.length() == 15) {
    Log.i(TAG,"isIDCard number.length() == 15 old IDCard");
    currentString=number;
    return true;
  }
  if (number.length() == 18) {
    currentString=number;
    return true;
  }
  return false;
}
