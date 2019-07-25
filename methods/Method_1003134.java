/** 
 * Try to open the file.
 * @param fileName the file name
 * @param readOnly whether the file should only be opened in read-only mode,even if the file is writable
 * @param encryptionKey the encryption key, or null if encryption is notused
 */
public void open(String fileName,boolean readOnly,char[] encryptionKey){
  if (file != null) {
    return;
  }
  FilePathCache.INSTANCE.getScheme();
  FilePath p=FilePath.get(fileName);
  if (p instanceof FilePathDisk && !fileName.startsWith(p.getScheme() + ":")) {
    FilePathNio.class.getName();
    fileName="nio:" + fileName;
  }
  this.fileName=fileName;
  FilePath f=FilePath.get(fileName);
  FilePath parent=f.getParent();
  if (parent != null && !parent.exists()) {
    throw DataUtils.newIllegalArgumentException("Directory does not exist: {0}",parent);
  }
  if (f.exists() && !f.canWrite()) {
    readOnly=true;
  }
  this.readOnly=readOnly;
  try {
    file=f.open(readOnly ? "r" : "rw");
    if (encryptionKey != null) {
      byte[] key=FilePathEncrypt.getPasswordBytes(encryptionKey);
      encryptedFile=file;
      file=new FilePathEncrypt.FileEncrypt(fileName,key,file);
    }
    try {
      if (readOnly) {
        fileLock=file.tryLock(0,Long.MAX_VALUE,true);
      }
 else {
        fileLock=file.tryLock();
      }
    }
 catch (    OverlappingFileLockException e) {
      throw DataUtils.newIllegalStateException(DataUtils.ERROR_FILE_LOCKED,"The file is locked: {0}",fileName,e);
    }
    if (fileLock == null) {
      try {
        close();
      }
 catch (      Exception ignore) {
      }
      throw DataUtils.newIllegalStateException(DataUtils.ERROR_FILE_LOCKED,"The file is locked: {0}",fileName);
    }
    fileSize=file.size();
  }
 catch (  IOException e) {
    try {
      close();
    }
 catch (    Exception ignore) {
    }
    throw DataUtils.newIllegalStateException(DataUtils.ERROR_READING_FAILED,"Could not open file {0}",fileName,e);
  }
}
