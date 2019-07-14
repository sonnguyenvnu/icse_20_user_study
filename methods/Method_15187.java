/** 
 * ??edittext????????
 * @param context
 * @param et
 * @return
 */
public static boolean isInputedCorrect(Activity context,EditText et){
  return isInputedCorrect(context,et,TYPE_NOT_ALLOWED_EMPTY,null);
}
