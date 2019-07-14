/** 
 * Creates and returns a SimpleAdapter for External Photos
 * @param context The Context
 * @return The SimpleAdapter for local photo
 */
public static ContentProviderSimpleAdapter getExternalPhotoSimpleAdapter(Context context){
  return new ContentProviderSimpleAdapter(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,context);
}
