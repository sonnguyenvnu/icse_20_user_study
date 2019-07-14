public SmbFile getSmbFile(int timeout){
  try {
    SmbFile smbFile=new SmbFile(path);
    smbFile.setConnectTimeout(timeout);
    return smbFile;
  }
 catch (  MalformedURLException e) {
    return null;
  }
}
