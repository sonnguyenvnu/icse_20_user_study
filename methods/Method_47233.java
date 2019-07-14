public void onNavigationItemActionClick(MenuItem item){
  String title=item.getTitle().toString();
  MenuMetadata meta=dataUtils.getDrawerMetadata(item);
  String path=meta.path;
switch (item.getGroupId()) {
case STORAGES_GROUP:
    if (!path.equals("/")) {
      GeneralDialogCreation.showPropertiesDialogForStorage(RootHelper.generateBaseFile(new File(path),true),mainActivity,mainActivity.getAppTheme());
    }
  break;
case SERVERS_GROUP:
case CLOUDS_GROUP:
case FOLDERS_GROUP:
if (dataUtils.containsBooks(new String[]{title,path}) != -1) {
  mainActivity.renameBookmark(title,path);
}
 else if (path.startsWith("smb:/")) {
  mainActivity.showSMBDialog(title,path,true);
}
 else if (path.startsWith("ssh:/")) {
  mainActivity.showSftpDialog(title,path,true);
}
 else if (path.startsWith(CloudHandler.CLOUD_PREFIX_DROPBOX)) {
  GeneralDialogCreation.showCloudDialog(mainActivity,mainActivity.getAppTheme(),OpenMode.DROPBOX);
}
 else if (path.startsWith(CloudHandler.CLOUD_PREFIX_GOOGLE_DRIVE)) {
  GeneralDialogCreation.showCloudDialog(mainActivity,mainActivity.getAppTheme(),OpenMode.GDRIVE);
}
 else if (path.startsWith(CloudHandler.CLOUD_PREFIX_BOX)) {
  GeneralDialogCreation.showCloudDialog(mainActivity,mainActivity.getAppTheme(),OpenMode.BOX);
}
 else if (path.startsWith(CloudHandler.CLOUD_PREFIX_ONE_DRIVE)) {
  GeneralDialogCreation.showCloudDialog(mainActivity,mainActivity.getAppTheme(),OpenMode.ONEDRIVE);
}
}
}
