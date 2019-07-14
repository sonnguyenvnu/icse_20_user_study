/** 
 * ??edittext????????
 * @param context
 * @param et
 * @param errorRemind
 * @return
 */
public static boolean isInputedCorrect(Activity context,EditText et,String errorRemind){
  return isInputedCorrect(context,et,TYPE_NOT_ALLOWED_EMPTY,errorRemind);
}
