/** 
 * Read a .ods (OpenDoc spreadsheet) zip file from an InputStream, and return the InputStream for content.xml contained inside.
 */
private InputStream odsFindContentXML(InputStream input){
  ZipInputStream zis=new ZipInputStream(input);
  ZipEntry entry=null;
  try {
    while ((entry=zis.getNextEntry()) != null) {
      if (entry.getName().equals("content.xml")) {
        return zis;
      }
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return null;
}
