/** 
 * Checks if the Media Type is a DRM Media Type
 * @param mimetype Media Type to check
 * @return True if the Media Type is DRM else false
 */
public static boolean isDrmMimeType(Context context,String mimetype){
  boolean result=false;
  if (context != null) {
    try {
      DrmManagerClient drmClient=new DrmManagerClient(context);
      if (drmClient != null && mimetype != null && mimetype.length() > 0) {
        result=drmClient.canHandle("",mimetype);
      }
    }
 catch (    IllegalArgumentException e) {
      Timber.w("DrmManagerClient instance could not be created, context is Illegal.");
    }
catch (    IllegalStateException e) {
      Timber.w("DrmManagerClient didn't initialize properly.");
    }
  }
  return result;
}
