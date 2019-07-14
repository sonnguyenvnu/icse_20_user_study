/** 
 * Checks whether the target path exists or is writable
 * @param f the target path
 * @return 1 if exists or writable, 0 if not writable
 */
public static int checkFolder(final String f,Context context){
  if (f == null)   return 0;
  if (f.startsWith("smb://") || f.startsWith("ssh://") || f.startsWith(OTGUtil.PREFIX_OTG) || f.startsWith(CloudHandler.CLOUD_PREFIX_BOX) || f.startsWith(CloudHandler.CLOUD_PREFIX_GOOGLE_DRIVE) || f.startsWith(CloudHandler.CLOUD_PREFIX_DROPBOX) || f.startsWith(CloudHandler.CLOUD_PREFIX_ONE_DRIVE))   return 1;
  File folder=new File(f);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && FileUtil.isOnExtSdCard(folder,context)) {
    if (!folder.exists() || !folder.isDirectory()) {
      return 0;
    }
    if (FileUtil.isWritableNormalOrSaf(folder,context)) {
      return 1;
    }
  }
 else   if (Build.VERSION.SDK_INT == 19 && FileUtil.isOnExtSdCard(folder,context)) {
    return 1;
  }
 else   if (folder.canWrite()) {
    return 1;
  }
 else {
    return 0;
  }
  return 0;
}
