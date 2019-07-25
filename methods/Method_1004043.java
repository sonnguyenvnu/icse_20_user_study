/** 
 * Dumps the given binary content under the given name if a non- <code>null</code> location has been specified.
 * @param name qualified class name in VM notation
 * @param contents binary contents
 * @throws IOException in case of problems while dumping the file
 */
void dump(final String name,final byte[] contents) throws IOException {
  if (location != null) {
    final File outputdir;
    final String localname;
    final int pkgpos=name.lastIndexOf('/');
    if (pkgpos != -1) {
      outputdir=new File(location,name.substring(0,pkgpos));
      localname=name.substring(pkgpos + 1);
    }
 else {
      outputdir=location;
      localname=name;
    }
    outputdir.mkdirs();
    final Long id=Long.valueOf(CRC64.classId(contents));
    final File file=new File(outputdir,String.format("%s.%016x.class",localname,id));
    final OutputStream out=new FileOutputStream(file);
    out.write(contents);
    out.close();
  }
}
