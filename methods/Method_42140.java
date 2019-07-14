/** 
 * Set a shared preference for an Uri.
 * @param context context
 * @param uri          the target value of the preference.
 */
public static void saveSdCardInfo(Context context,@Nullable final Uri uri){
  Hawk.put(context.getString(R.string.preference_internal_uri_extsdcard_photos),uri == null ? null : uri.toString());
  Hawk.put("sd_card_path",StorageHelper.getSdcardPath(context));
}
