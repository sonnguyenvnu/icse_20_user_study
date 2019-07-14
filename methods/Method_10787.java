public static String file2Base64(String filePath){
  FileInputStream fis=null;
  String base64String="";
  ByteArrayOutputStream bos=new ByteArrayOutputStream();
  try {
    fis=new FileInputStream(filePath);
    byte[] buffer=new byte[1024 * 100];
    int count=0;
    while ((count=fis.read(buffer)) != -1) {
      bos.write(buffer,0,count);
    }
    fis.close();
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  base64String=Base64.encodeToString(bos.toByteArray(),Base64.DEFAULT);
  return base64String;
}
