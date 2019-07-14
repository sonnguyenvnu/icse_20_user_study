/** 
 * ??edittext????????
 * @param context
 * @param et
 * @param type
 * @param stringResId
 * @return
 */
public static boolean isInputedCorrect(Activity context,EditText et,int type,int stringResId){
  try {
    if (context != null && stringResId > 0) {
      return isInputedCorrect(context,et,type,context.getResources().getString(stringResId));
    }
  }
 catch (  Exception e) {
    Log.e(TAG,"isInputedCorrect try { if (context != null && stringResId > 0) {catch (Exception e) \n" + e.getMessage());
  }
  return false;
}
