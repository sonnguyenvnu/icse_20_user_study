public static File downLoadFile(Activity context,String name,String suffix,String httpUrl){
  final String fileName=name + StringUtil.getTrimedString(suffix);
  final File file=new File(DataKeeper.fileRootPath + fileName);
  try {
    httpUrl=StringUtil.getCorrectUrl(httpUrl);
    if (httpUrl.endsWith("/")) {
      httpUrl=httpUrl.substring(0,httpUrl.length() - 1);
    }
    URL url=new URL(httpUrl);
    try {
      HttpURLConnection conn=(HttpURLConnection)url.openConnection();
      InputStream is=conn.getInputStream();
      FileOutputStream fos=new FileOutputStream(file);
      byte[] buf=new byte[256];
      conn.connect();
      double count=0;
      if (conn.getResponseCode() >= 400) {
        CommonUtil.showShortToast(context,"????");
      }
 else {
        while (count <= 100) {
          if (is != null) {
            int numRead=is.read(buf);
            if (numRead <= 0) {
              break;
            }
 else {
              fos.write(buf,0,numRead);
            }
          }
 else {
            break;
          }
        }
      }
      conn.disconnect();
      fos.close();
      is.close();
    }
 catch (    IOException e) {
      Log.e(TAG,"downLoadFile   try { HttpURLConnection conn = (HttpURLConnection) url ... " + "} catch (IOException e) {\n" + e.getMessage());
    }
  }
 catch (  MalformedURLException e) {
    Log.e(TAG,"downLoadFile   try {  URL url = new URL(httpUrl); ... " + "} catch (IOException e) {\n" + e.getMessage());
  }
  return file;
}
