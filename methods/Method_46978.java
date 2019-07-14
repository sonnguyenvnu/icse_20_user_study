public void removeFromDatabase(OperationData operationData){
  AppConfig.runInBackground(() -> {
switch (operationData.type) {
case HIDDEN:
case HISTORY:
case LIST:
case GRID:
      removePath(operationData.type,operationData.path);
    break;
case BOOKMARKS:
  removeBookmarksPath(operationData.name,operationData.path);
break;
case SMB:
removeSmbPath(operationData.name,operationData.path);
break;
case SFTP:
removeSftpPath(operationData.name,operationData.path);
break;
default :
throw new IllegalStateException("Unidentified operation!");
}
}
);
}
