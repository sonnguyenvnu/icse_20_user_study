/** 
 * Create a new  {@link Navigation} to open a URL.
 * @param context The context.
 * @param url The source URL.
 * @return A reference to the {@link Navigation}.
 */
@NonNull @CheckResult public static Navigation navigation(@NonNull Context context,@NonNull String url){
  return new Floo(context,url.trim());
}
