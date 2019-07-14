/** 
 * Get a  {@link FingerprintManagerCompat} instance for a provided context. 
 */
public static FingerprintManagerCompat from(Context context){
  return new FingerprintManagerCompat(context);
}
