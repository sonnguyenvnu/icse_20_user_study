/** 
 * ?????????
 * @param zipFile
 * @param descDir
 * @author isea533
 */
@SuppressWarnings("rawtypes") public static List<String> unZipFiles(File zipFile,String descDir) throws IOException {
  List<String> result=new ArrayList<String>();
  File pathFile=new File(descDir);
  if (!pathFile.exists()) {
    pathFile.mkdirs();
  }
  Charset charset=Charset.forName("GBK");
  ZipFile zip=new ZipFile(zipFile,charset);
  for (Enumeration entries=zip.entries(); entries.hasMoreElements(); ) {
    ZipEntry entry=(ZipEntry)entries.nextElement();
    String zipEntryName=entry.getName();
    InputStream in=zip.getInputStream(entry);
    String outPath=(descDir + zipEntryName).replaceAll("\\*","/");
    ;
    File file=new File(outPath.substring(0,outPath.lastIndexOf('/')));
    if (!file.exists()) {
      file.mkdirs();
    }
    if (new File(outPath).isDirectory()) {
      continue;
    }
    result.add(outPath);
    OutputStream out=new FileOutputStream(outPath);
    byte[] buf1=new byte[1024];
    int len;
    while ((len=in.read(buf1)) > 0) {
      out.write(buf1,0,len);
    }
    in.close();
    out.close();
  }
  return result;
}
