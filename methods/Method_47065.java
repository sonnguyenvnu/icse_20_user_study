public static void mkdir(@NonNull final HybridFile file,final Context context,final boolean rootMode,@NonNull final ErrorCallBack errorCallBack){
  new AsyncTask<Void,Void,Void>(){
    @Override protected Void doInBackground(    Void... params){
      if (!Operations.isFileNameValid(file.getName(context))) {
        errorCallBack.invalidName(file);
        return null;
      }
      if (file.exists()) {
        errorCallBack.exists(file);
        return null;
      }
      if (file.isSftp()) {
        file.mkdir(context);
        return null;
      }
      if (file.isSmb()) {
        try {
          file.getSmbFile(2000).mkdirs();
        }
 catch (        SmbException e) {
          e.printStackTrace();
          errorCallBack.done(file,false);
          return null;
        }
        errorCallBack.done(file,file.exists());
        return null;
      }
 else       if (file.isOtgFile()) {
        DocumentFile directoryToCreate=OTGUtil.getDocumentFile(file.getPath(),context,false);
        if (directoryToCreate != null)         errorCallBack.exists(file);
        DocumentFile parentDirectory=OTGUtil.getDocumentFile(file.getParent(),context,false);
        if (parentDirectory.isDirectory()) {
          parentDirectory.createDirectory(file.getName(context));
          errorCallBack.done(file,true);
        }
 else         errorCallBack.done(file,false);
        return null;
      }
 else       if (file.isDropBoxFile()) {
        CloudStorage cloudStorageDropbox=dataUtils.getAccount(OpenMode.DROPBOX);
        try {
          cloudStorageDropbox.createFolder(CloudUtil.stripPath(OpenMode.DROPBOX,file.getPath()));
          errorCallBack.done(file,true);
        }
 catch (        Exception e) {
          e.printStackTrace();
          errorCallBack.done(file,false);
        }
      }
 else       if (file.isBoxFile()) {
        CloudStorage cloudStorageBox=dataUtils.getAccount(OpenMode.BOX);
        try {
          cloudStorageBox.createFolder(CloudUtil.stripPath(OpenMode.BOX,file.getPath()));
          errorCallBack.done(file,true);
        }
 catch (        Exception e) {
          e.printStackTrace();
          errorCallBack.done(file,false);
        }
      }
 else       if (file.isOneDriveFile()) {
        CloudStorage cloudStorageOneDrive=dataUtils.getAccount(OpenMode.ONEDRIVE);
        try {
          cloudStorageOneDrive.createFolder(CloudUtil.stripPath(OpenMode.ONEDRIVE,file.getPath()));
          errorCallBack.done(file,true);
        }
 catch (        Exception e) {
          e.printStackTrace();
          errorCallBack.done(file,false);
        }
      }
 else       if (file.isGoogleDriveFile()) {
        CloudStorage cloudStorageGdrive=dataUtils.getAccount(OpenMode.GDRIVE);
        try {
          cloudStorageGdrive.createFolder(CloudUtil.stripPath(OpenMode.GDRIVE,file.getPath()));
          errorCallBack.done(file,true);
        }
 catch (        Exception e) {
          e.printStackTrace();
          errorCallBack.done(file,false);
        }
      }
 else {
        if (file.isLocal() || file.isRoot()) {
          int mode=checkFolder(new File(file.getParent()),context);
          if (mode == 2) {
            errorCallBack.launchSAF(file);
            return null;
          }
          if (mode == 1 || mode == 0)           FileUtil.mkdir(file.getFile(),context);
          if (!file.exists() && rootMode) {
            file.setMode(OpenMode.ROOT);
            if (file.exists())             errorCallBack.exists(file);
            try {
              RootUtils.mkDir(file.getParent(context),file.getName(context));
            }
 catch (            ShellNotRunningException e) {
              e.printStackTrace();
            }
            errorCallBack.done(file,file.exists());
            return null;
          }
          errorCallBack.done(file,file.exists());
          return null;
        }
        errorCallBack.done(file,file.exists());
      }
      return null;
    }
  }
.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
}
