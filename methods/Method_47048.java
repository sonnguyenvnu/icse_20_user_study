/** 
 * Helper method to get length of folder in an otg
 */
public long folderSize(Context context){
  long size=0l;
switch (mode) {
case SFTP:
    return SshClientUtils.execute(new SFtpClientTemplate(path){
      @Override public Long execute(      SFTPClient client) throws IOException {
        return client.size(SshClientUtils.extractRemotePathFrom(path));
      }
    }
);
case SMB:
  try {
    size=FileUtils.folderSize(new SmbFile(path));
  }
 catch (  MalformedURLException e) {
    size=0l;
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
case OTG:
size=FileUtils.otgFolderSize(path,context);
break;
case DROPBOX:
case BOX:
case GDRIVE:
case ONEDRIVE:
size=FileUtils.folderSizeCloud(mode,dataUtils.getAccount(mode).getMetadata(CloudUtil.stripPath(mode,path)));
break;
default :
return 0l;
}
return size;
}
