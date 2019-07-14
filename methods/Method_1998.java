/** 
 * Creates and returns a SimpleAdapter for Internal Photos
 * @param context The Context
 * @return The SimpleAdapter for local photo
 */
public static ContentProviderSimpleAdapter getInternalPhotoSimpleAdapter(Context context){
  return new ContentProviderSimpleAdapter(MediaStore.Images.Media.INTERNAL_CONTENT_URI,context);
}
