/** 
 * ???????????
 * @return ?????????
 */
public static String getUniqueSerialNumber(){
  String phoneName=Build.MODEL;
  String manuFacturer=Build.MANUFACTURER;
  Log.d("?????",manuFacturer + "-" + phoneName + "-" + getSerialNumber());
  return manuFacturer + "-" + phoneName + "-" + getSerialNumber();
}
