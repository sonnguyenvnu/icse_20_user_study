protected Boolean doInBackground(ArrayList<HybridFileParcelable>... p1){
  files=p1[0];
  boolean wasDeleted=true;
  if (files.size() == 0)   return true;
  if (files.get(0).isOtgFile()) {
    for (    HybridFileParcelable file : files) {
      DocumentFile documentFile=OTGUtil.getDocumentFile(file.getPath(),cd,false);
      wasDeleted=documentFile.delete();
    }
  }
 else   if (files.get(0).isDropBoxFile()) {
    CloudStorage cloudStorageDropbox=dataUtils.getAccount(OpenMode.DROPBOX);
    for (    HybridFileParcelable baseFile : files) {
      try {
        cloudStorageDropbox.delete(CloudUtil.stripPath(OpenMode.DROPBOX,baseFile.getPath()));
      }
 catch (      Exception e) {
        e.printStackTrace();
        wasDeleted=false;
        break;
      }
    }
  }
 else   if (files.get(0).isBoxFile()) {
    CloudStorage cloudStorageBox=dataUtils.getAccount(OpenMode.BOX);
    for (    HybridFileParcelable baseFile : files) {
      try {
        cloudStorageBox.delete(CloudUtil.stripPath(OpenMode.BOX,baseFile.getPath()));
      }
 catch (      Exception e) {
        e.printStackTrace();
        wasDeleted=false;
        break;
      }
    }
  }
 else   if (files.get(0).isGoogleDriveFile()) {
    CloudStorage cloudStorageGdrive=dataUtils.getAccount(OpenMode.GDRIVE);
    for (    HybridFileParcelable baseFile : files) {
      try {
        cloudStorageGdrive.delete(CloudUtil.stripPath(OpenMode.GDRIVE,baseFile.getPath()));
      }
 catch (      Exception e) {
        e.printStackTrace();
        wasDeleted=false;
        break;
      }
    }
  }
 else   if (files.get(0).isOneDriveFile()) {
    CloudStorage cloudStorageOnedrive=dataUtils.getAccount(OpenMode.ONEDRIVE);
    for (    HybridFileParcelable baseFile : files) {
      try {
        cloudStorageOnedrive.delete(CloudUtil.stripPath(OpenMode.ONEDRIVE,baseFile.getPath()));
      }
 catch (      Exception e) {
        e.printStackTrace();
        wasDeleted=false;
        break;
      }
    }
  }
 else {
    for (    HybridFileParcelable file : files) {
      try {
        if (file.delete(cd,rootMode)) {
          wasDeleted=true;
        }
 else {
          wasDeleted=false;
          break;
        }
      }
 catch (      ShellNotRunningException e) {
        e.printStackTrace();
        wasDeleted=false;
        break;
      }
    }
  }
  if (!files.get(0).isSmb()) {
    try {
      for (      HybridFileParcelable f : files) {
        delete(cd,f.getPath());
      }
    }
 catch (    Exception e) {
      for (      HybridFileParcelable f : files) {
        FileUtils.scanFile(f.getFile(),cd);
      }
    }
  }
  for (  HybridFileParcelable file : files) {
    if (file.getName().endsWith(CryptUtil.CRYPT_EXTENSION)) {
      CryptHandler handler=new CryptHandler(cd);
      handler.clear(file.getPath());
    }
  }
  return wasDeleted;
}
