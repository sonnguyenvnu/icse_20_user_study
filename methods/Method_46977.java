public void saveToDatabase(OperationData operationData){
  AppConfig.runInBackground(() -> {
switch (operationData.type) {
case HIDDEN:
case HISTORY:
case LIST:
case GRID:
      setPath(operationData.type,operationData.path);
    break;
case BOOKMARKS:
case SMB:
  setPath(operationData.type,operationData.name,operationData.path);
break;
case SFTP:
addSsh(operationData.name,operationData.path,operationData.hostKey,operationData.sshKeyName,operationData.sshKey);
break;
default :
throw new IllegalStateException("Unidentified operation!");
}
}
);
}
