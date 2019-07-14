private SmbFile createSMBPath(String[] auth,boolean anonym){
  try {
    String yourPeerIP=auth[0], domain=auth[3];
    String path="smb://" + (android.text.TextUtils.isEmpty(domain) ? "" : (URLEncoder.encode(domain + ";","UTF-8"))) + (anonym ? "" : (URLEncoder.encode(auth[1],"UTF-8") + ":" + URLEncoder.encode(auth[2],"UTF-8") + "@")) + yourPeerIP + "/";
    SmbFile smbFile=new SmbFile(path);
    return smbFile;
  }
 catch (  MalformedURLException e) {
    e.printStackTrace();
  }
catch (  UnsupportedEncodingException e) {
    e.printStackTrace();
  }
  return null;
}
