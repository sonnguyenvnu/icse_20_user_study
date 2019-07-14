/** 
 * Method determines if there is something to go back to
 */
public static boolean canGoBack(Context context,HybridFile currentFile){
switch (currentFile.getMode()) {
case DROPBOX:
case BOX:
case GDRIVE:
case ONEDRIVE:
case OTG:
case SFTP:
    return true;
default :
  return true;
}
}
