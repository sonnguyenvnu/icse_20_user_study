/** 
 * Continue the process of magical exporting. This function can be called recursively to walk through folders looking for more goodies that will be added to the ZipOutputStream.
 */
static protected void packClassPathIntoZipFileRecursive(File dir,String sofar,ZipOutputStream zos) throws IOException {
  String files[]=dir.list();
  for (int i=0; i < files.length; i++) {
    if (files[i].charAt(0) == '.')     continue;
    File sub=new File(dir,files[i]);
    String nowfar=(sofar == null) ? files[i] : (sofar + "/" + files[i]);
    if (sub.isDirectory()) {
      packClassPathIntoZipFileRecursive(sub,nowfar,zos);
    }
 else {
      if (!files[i].toLowerCase().endsWith(".jar") && !files[i].toLowerCase().endsWith(".zip") && files[i].charAt(0) != '.') {
        ZipEntry entry=new ZipEntry(nowfar);
        zos.putNextEntry(entry);
        PApplet.saveStream(zos,new FileInputStream(sub));
        zos.closeEntry();
      }
    }
  }
}
