public static boolean mkdirs(Context context,HybridFile file){
  boolean isSuccessful=true;
switch (file.mode) {
case SMB:
    try {
      SmbFile smbFile=new SmbFile(file.getPath());
      smbFile.mkdirs();
    }
 catch (    MalformedURLException e) {
      e.printStackTrace();
      isSuccessful=false;
    }
catch (    SmbException e) {
      e.printStackTrace();
      isSuccessful=false;
    }
  break;
case OTG:
DocumentFile documentFile=OTGUtil.getDocumentFile(file.getPath(),context,true);
isSuccessful=documentFile != null;
break;
case FILE:
isSuccessful=mkdir(new File(file.getPath()),context);
break;
default :
isSuccessful=true;
break;
}
return isSuccessful;
}
