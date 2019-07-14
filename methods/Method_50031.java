/** 
 * Modifies the file extension for a DRM Forward Lock file NOTE: This function shouldn't be called if the file shouldn't be DRM converted
 */
public static String modifyDrmFwLockFileExtension(String filename){
  if (filename != null) {
    int extensionIndex;
    extensionIndex=filename.lastIndexOf(".");
    if (extensionIndex != -1) {
      filename=filename.substring(0,extensionIndex);
    }
    filename=filename.concat(EXTENSION_INTERNAL_FWDL);
  }
  return filename;
}
