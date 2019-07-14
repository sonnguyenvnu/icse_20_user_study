/** 
 * Helper method to list children of this file
 * @deprecated use forEachChildrenFile()
 */
public ArrayList<HybridFileParcelable> listFiles(Context context,boolean isRoot){
  ArrayList<HybridFileParcelable> arrayList=new ArrayList<>();
switch (mode) {
case SFTP:
    try {
      arrayList=SshClientUtils.execute(new SFtpClientTemplate(path){
        @Override public ArrayList<HybridFileParcelable> execute(        SFTPClient client){
          ArrayList<HybridFileParcelable> retval=new ArrayList<HybridFileParcelable>();
          try {
            for (            RemoteResourceInfo info : client.ls(SshClientUtils.extractRemotePathFrom(path))) {
              HybridFileParcelable f=new HybridFileParcelable(String.format("%s/%s",path,info.getName()));
              f.setName(info.getName());
              f.setMode(OpenMode.SFTP);
              f.setDirectory(info.isDirectory());
              f.setDate(info.getAttributes().getMtime() * 1000);
              f.setSize(f.isDirectory() ? 0 : info.getAttributes().getSize());
              f.setPermission(Integer.toString(FilePermission.toMask(info.getAttributes().getPermissions()),8));
              retval.add(f);
            }
          }
 catch (          IOException e) {
            Log.w("DEBUG.listFiles","IOException",e);
          }
          return retval;
        }
      }
);
    }
 catch (    Exception e) {
      e.printStackTrace();
      arrayList.clear();
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
    arrayList.add(baseFile);
  }
}
 catch (MalformedURLException e) {
  if (arrayList != null)   arrayList.clear();
  e.printStackTrace();
}
catch (SmbException e) {
  if (arrayList != null)   arrayList.clear();
  e.printStackTrace();
}
break;
case OTG:
arrayList=OTGUtil.getDocumentFilesList(path,context);
break;
case DROPBOX:
case BOX:
case GDRIVE:
case ONEDRIVE:
try {
arrayList=CloudUtil.listFiles(path,dataUtils.getAccount(mode),mode);
}
 catch (CloudPluginException e) {
e.printStackTrace();
arrayList=new ArrayList<>();
}
break;
default :
arrayList=RootHelper.getFilesList(path,isRoot,true,null);
}
return arrayList;
}
