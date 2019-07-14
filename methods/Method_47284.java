/** 
 * Strips down the cloud path to remove any prefix
 */
public static String stripPath(OpenMode openMode,String path){
  String strippedPath=path;
switch (openMode) {
case DROPBOX:
    if (path.equals(CloudHandler.CLOUD_PREFIX_DROPBOX + "/")) {
      strippedPath=path.replace(CloudHandler.CLOUD_PREFIX_DROPBOX,"");
    }
 else {
      strippedPath=path.replace(CloudHandler.CLOUD_PREFIX_DROPBOX + "/","");
    }
  break;
case BOX:
if (path.equals(CloudHandler.CLOUD_PREFIX_BOX + "/")) {
  strippedPath=path.replace(CloudHandler.CLOUD_PREFIX_BOX,"");
}
 else {
  strippedPath=path.replace(CloudHandler.CLOUD_PREFIX_BOX + "/","");
}
break;
case ONEDRIVE:
if (path.equals(CloudHandler.CLOUD_PREFIX_ONE_DRIVE + "/")) {
strippedPath=path.replace(CloudHandler.CLOUD_PREFIX_ONE_DRIVE,"");
}
 else {
strippedPath=path.replace(CloudHandler.CLOUD_PREFIX_ONE_DRIVE + "/","");
}
break;
case GDRIVE:
if (path.equals(CloudHandler.CLOUD_PREFIX_GOOGLE_DRIVE + "/")) {
strippedPath=path.replace(CloudHandler.CLOUD_PREFIX_GOOGLE_DRIVE,"");
}
 else {
strippedPath=path.replace(CloudHandler.CLOUD_PREFIX_GOOGLE_DRIVE + "/","");
}
break;
default :
break;
}
return strippedPath;
}
