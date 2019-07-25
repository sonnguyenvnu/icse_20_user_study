/** 
 * ??
 */
public static boolean copy(String src,String des,boolean delete){
  File file=new File(src);
  if (!file.exists()) {
    return false;
  }
  File desFile=new File(des);
  FileInputStream in=null;
  FileOutputStream out=null;
  try {
    in=new FileInputStream(file);
    out=new FileOutputStream(desFile);
    byte[] buffer=new byte[1024];
    int count=-1;
    while ((count=in.read(buffer)) != -1) {
      out.write(buffer,0,count);
      out.flush();
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
 finally {
    IOUtils.close(in);
    IOUtils.close(out);
  }
  if (delete) {
    file.delete();
  }
  return true;
}
