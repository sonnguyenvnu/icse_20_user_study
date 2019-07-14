/** 
 * ????sourceFilePath???????????fileName???zip???????zipFilePath???
 * @param sourceFilePath :????????
 * @param zipFilePath    :???????
 * @param fileName       :????????
 * @return
 */
public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){
  boolean flag=false;
  File sourceFile=new File(sourceFilePath);
  FileInputStream fis=null;
  BufferedInputStream bis=null;
  FileOutputStream fos=null;
  ZipOutputStream zos=null;
  if (sourceFile.exists()) {
    try {
      File zipFile=new File(zipFilePath + "/" + fileName + ".zip");
      if (zipFile.exists()) {
        System.out.println(zipFilePath + "????????:" + fileName + ".zip" + "????.");
      }
 else {
        File[] sourceFiles=sourceFile.listFiles();
        if (null == sourceFiles || sourceFiles.length < 1) {
          System.out.println("?????????" + sourceFilePath + "????????????.");
        }
 else {
          fos=new FileOutputStream(zipFile);
          zos=new ZipOutputStream(new BufferedOutputStream(fos));
          byte[] bufs=new byte[1024 * 10];
          for (int i=0; i < sourceFiles.length; i++) {
            ZipEntry zipEntry=new ZipEntry(sourceFiles[i].getName());
            fis=new FileInputStream(sourceFiles[i]);
            bis=new BufferedInputStream(fis,1024 * 10);
            int read=0;
            while ((read=bis.read(bufs,0,1024 * 10)) != -1) {
              zos.write(bufs,0,read);
            }
          }
          flag=true;
        }
      }
    }
 catch (    IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
 finally {
      try {
        if (null != bis) {
          bis.close();
        }
        if (null != zos) {
          zos.close();
        }
      }
 catch (      IOException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    }
  }
 else {
    System.out.println("?????????" + sourceFilePath + "???.");
  }
  return flag;
}
