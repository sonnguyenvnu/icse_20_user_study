/** 
 * ??edittext????????
 * @param context
 * @param stringResId
 * @param et
 * @return
 */
public static boolean isInputedCorrect(Activity context,int stringResId,EditText et){
  return isInputedCorrect(context,et,TYPE_NOT_ALLOWED_EMPTY,stringResId);
}
