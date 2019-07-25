/** 
 * Makes sure that the given file is a simple dex file containing the given classes. Creates the file if that's not the case.
 */
public static File ensure(File file,String childClz,String superClz) throws IOException {
  try {
    byte[] dex=inputStreamToByteArray(new FileInputStream(file));
    if (matches(dex,childClz,superClz)) {
      return file;
    }
 else {
      file.delete();
    }
  }
 catch (  IOException e) {
    file.delete();
  }
  byte[] dex=create(childClz,superClz);
  FileOutputStream fos=new FileOutputStream(file);
  fos.write(dex);
  fos.close();
  return file;
}
