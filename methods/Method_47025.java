/** 
 * Determine the main folder of the external SD card containing the given file.
 * @param file the file.
 * @return The main folder of the external SD card containing this file, if the file is on an SD card. Otherwise,null is returned.
 */
@TargetApi(Build.VERSION_CODES.KITKAT) private static String getExtSdCardFolder(final File file,Context context){
  String[] extSdPaths=getExtSdCardPaths(context);
  try {
    for (int i=0; i < extSdPaths.length; i++) {
      if (file.getCanonicalPath().startsWith(extSdPaths[i])) {
        return extSdPaths[i];
      }
    }
  }
 catch (  IOException e) {
    return null;
  }
  return null;
}
