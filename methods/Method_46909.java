@Override protected Boolean doInBackground(ArrayList<String>... strings){
  paths=strings[0];
  if (files.size() == 0)   return true;
  for (  ArrayList<HybridFileParcelable> filesCurrent : files) {
    totalBytes+=FileUtils.getTotalBytes(filesCurrent,context);
  }
  HybridFile destination=new HybridFile(mode,paths.get(0));
  destinationSize=destination.getUsableSpace();
switch (mode) {
case SMB:
    for (int i=0; i < paths.size(); i++) {
      for (      HybridFileParcelable f : files.get(i)) {
        try {
          SmbFile source=new SmbFile(f.getPath());
          SmbFile dest=new SmbFile(paths.get(i) + "/" + f.getName());
          source.renameTo(dest);
        }
 catch (        MalformedURLException e) {
          e.printStackTrace();
          return false;
        }
catch (        SmbException e) {
          e.printStackTrace();
          return false;
        }
      }
    }
  break;
case FILE:
for (int i=0; i < paths.size(); i++) {
  for (  HybridFileParcelable f : files.get(i)) {
    File dest=new File(paths.get(i) + "/" + f.getName());
    File source=new File(f.getPath());
    if (!source.renameTo(dest)) {
      if (mainFrag.getMainActivity().isRootExplorer()) {
        try {
          if (!RootUtils.rename(f.getPath(),paths.get(i) + "/" + f.getName()))           return false;
        }
 catch (        ShellNotRunningException e) {
          e.printStackTrace();
          return false;
        }
      }
 else       return false;
    }
  }
}
break;
case DROPBOX:
case BOX:
case ONEDRIVE:
case GDRIVE:
for (int i=0; i < paths.size(); i++) {
for (HybridFileParcelable baseFile : files.get(i)) {
DataUtils dataUtils=DataUtils.getInstance();
CloudStorage cloudStorage=dataUtils.getAccount(mode);
String targetPath=paths.get(i) + "/" + baseFile.getName();
if (baseFile.getMode() == mode) {
  try {
    cloudStorage.move(CloudUtil.stripPath(mode,baseFile.getPath()),CloudUtil.stripPath(mode,targetPath));
  }
 catch (  Exception e) {
    return false;
  }
}
 else {
  return false;
}
}
}
default :
return false;
}
return true;
}
