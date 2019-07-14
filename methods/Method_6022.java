/** 
 * Makes a best guess to infer the type from a file name.
 * @param fileName Name of the file. It can include the path of the file.
 * @return The content type.
 */
@C.ContentType public static int inferContentType(String fileName){
  fileName=toLowerInvariant(fileName);
  if (fileName.endsWith(".mpd")) {
    return C.TYPE_DASH;
  }
 else   if (fileName.endsWith(".m3u8")) {
    return C.TYPE_HLS;
  }
 else   if (fileName.matches(".*\\.ism(l)?(/manifest(\\(.+\\))?)?")) {
    return C.TYPE_SS;
  }
 else {
    return C.TYPE_OTHER;
  }
}
