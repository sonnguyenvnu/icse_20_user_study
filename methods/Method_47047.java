/** 
 * @deprecated use {@link #folderSize(Context)}
 */
public long folderSize(){
  long size=0L;
switch (mode) {
case SFTP:
    return folderSize(AppConfig.getInstance());
case SMB:
  try {
    size=FileUtils.folderSize(new SmbFile(path));
  }
 catch (  MalformedURLException e) {
    size=0L;
    e.printStackTrace();
  }
break;
case FILE:
size=FileUtils.folderSize(new File(path),null);
break;
case ROOT:
HybridFileParcelable baseFile=generateBaseFileFromParent();
if (baseFile != null) size=baseFile.getSize();
break;
default :
return 0L;
}
return size;
}
