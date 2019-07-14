public String parseCloudPath(OpenMode serviceType,String path){
switch (serviceType) {
case DROPBOX:
    if (path.contains(CloudHandler.CLOUD_PREFIX_DROPBOX))     return path;
 else     return CloudHandler.CLOUD_PREFIX_DROPBOX + path.substring(path.indexOf(":") + 1,path.length());
case BOX:
  if (path.contains(CloudHandler.CLOUD_PREFIX_BOX))   return path;
 else   return CloudHandler.CLOUD_PREFIX_BOX + path.substring(path.indexOf(":") + 1,path.length());
case GDRIVE:
if (path.contains(CloudHandler.CLOUD_PREFIX_GOOGLE_DRIVE)) return path;
 else return CloudHandler.CLOUD_PREFIX_GOOGLE_DRIVE + path.substring(path.indexOf(":") + 1,path.length());
case ONEDRIVE:
if (path.contains(CloudHandler.CLOUD_PREFIX_ONE_DRIVE)) return path;
 else return CloudHandler.CLOUD_PREFIX_ONE_DRIVE + path.substring(path.indexOf(":") + 1,path.length());
default :
return path;
}
}
