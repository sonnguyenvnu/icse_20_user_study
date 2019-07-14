@Override public boolean handles(String s){
  return s.startsWith(CloudHandler.CLOUD_PREFIX_BOX) || s.startsWith(CloudHandler.CLOUD_PREFIX_DROPBOX) || s.startsWith(CloudHandler.CLOUD_PREFIX_GOOGLE_DRIVE) || s.startsWith(CloudHandler.CLOUD_PREFIX_ONE_DRIVE) || s.startsWith("smb:/") || s.startsWith("ssh:/");
}
