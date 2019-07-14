/** 
 * @deprecated Use {@link TextUtils#equals(CharSequence,CharSequence)} instead.
 */
public static boolean equalsAny(@Nullable CharSequence text,@Nullable CharSequence text1){
  return TextUtils.equals(text,text1);
}
