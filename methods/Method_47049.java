/** 
 * Helper method to list children of this file
 */
public void forEachChildrenFile(Context context,boolean isRoot,OnFileFound onFileFound){
switch (mode) {
case SFTP:
    try {
      SshClientUtils.execute(new SFtpClientTemplate(path){
        @Override public Void execute(        SFTPClient client){
          try {
            for (            RemoteResourceInfo info : client.ls(SshClientUtils.extractRemotePathFrom(path))) {
              boolean isDirectory=info.isDirectory();
              if (info.getAttributes().getType().equals(FileMode.Type.SYMLINK)) {
                FileAttributes symlinkAttrs=client.stat(info.getPath());
                isDirectory=symlinkAttrs.getType().equals(FileMode.Type.DIRECTORY);
              }
              HybridFileParcelable f=new HybridFileParcelable(String.format("%s/%s",path,info.getName()));
              f.setName(info.getName());
              f.setMode(OpenMode.SFTP);
              f.setDirectory(isDirectory);
              f.setDate(info.getAttributes().getMtime() * 1000);
              f.setSize(isDirectory ? 0 : info.getAttributes().getSize());
              f.setPermission(Integer.toString(FilePermission.toMask(info.getAttributes().getPermissions()),8));
              onFileFound.onFileFound(f);
            }
          }
 catch (          IOException e) {
            Log.w("DEBUG.listFiles","IOException",e);
          }
          return null;
        }
      }
);
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  break;
case SMB:
try {
  SmbFile smbFile=new SmbFile(path);
  for (  SmbFile smbFile1 : smbFile.listFiles()) {
    HybridFileParcelable baseFile=new HybridFileParcelable(smbFile1.getPath());
    baseFile.setName(smbFile1.getName());
    baseFile.setMode(OpenMode.SMB);
    baseFile.setDirectory(smbFile1.isDirectory());
    baseFile.setDate(smbFile1.lastModified());
    baseFile.setSize(baseFile.isDirectory() ? 0 : smbFile1.length());
    onFileFound.onFileFound(baseFile);
  }
}
 catch (MalformedURLException|SmbException e) {
  e.printStackTrace();
}
break;
case OTG:
OTGUtil.getDocumentFiles(path,context,onFileFound);
break;
case DROPBOX:
case BOX:
case GDRIVE:
case ONEDRIVE:
try {
CloudUtil.getCloudFiles(path,dataUtils.getAccount(mode),mode,onFileFound);
}
 catch (CloudPluginException e) {
e.printStackTrace();
}
break;
default :
RootHelper.getFiles(path,isRoot,true,null,onFileFound);
}
}
