/** 
 * @param context a a context
 * @return authority of this provider
 */
@NonNull private static String getAuthority(@NonNull Context context){
  return context.getPackageName() + ".acra";
}
