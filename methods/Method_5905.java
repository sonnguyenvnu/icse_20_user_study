/** 
 * Registers a custom MIME type. Most applications do not need to call this method, as handling of standard MIME types is built in. These built-in MIME types take precedence over any registered via this method. If this method is used, it must be called before creating any player(s).
 * @param mimeType The custom MIME type to register.
 * @param codecPrefix The RFC 6381-style codec string prefix associated with the MIME type.
 * @param trackType The {@link C}{@code .TRACK_TYPE_*} constant associated with the MIME type.This value is ignored if the top-level type of  {@code mimeType} is audio, video or text.
 */
public static void registerCustomMimeType(String mimeType,String codecPrefix,int trackType){
  CustomMimeType customMimeType=new CustomMimeType(mimeType,codecPrefix,trackType);
  int customMimeTypeCount=customMimeTypes.size();
  for (int i=0; i < customMimeTypeCount; i++) {
    if (mimeType.equals(customMimeTypes.get(i).mimeType)) {
      customMimeTypes.remove(i);
      break;
    }
  }
  customMimeTypes.add(customMimeType);
}
