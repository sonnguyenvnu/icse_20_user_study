/** 
 * Tries to guess the mime type from uri extension
 * @param uri the uri
 * @return the mime type of the uri, with fallback {@link #MIME_TYPE_OCTET_STREAM}
 */
@NonNull public static String guessMimeType(@NonNull Uri uri){
  String type=null;
  final String fileExtension=MimeTypeMap.getFileExtensionFromUrl(uri.toString());
  if (fileExtension != null) {
    type=MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase());
  }
  if (type == null) {
    type=MIME_TYPE_OCTET_STREAM;
  }
  return type;
}
