/** 
 * Returns a formatted number for the user's locale.  {@link NumberOptions} can control whether the number isused as a currency, if it is bucketed, and the precision.
 */
public static @NonNull String format(final float value,final @NonNull NumberOptions options){
  return format(value,options,Locale.getDefault());
}
