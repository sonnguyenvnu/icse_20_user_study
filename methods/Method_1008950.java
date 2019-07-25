/** 
 * convenience method to load from a file, where you know the Filetype (if it is Filetype.ZippedPackage, ZipFile will be used instead of ZipArchiveInputStream)
 * @param is
 * @param docxFormat
 * @return
 * @throws Docx4JException
 * @Since 6.0.0           
 */
public static OpcPackage load(final File file,Filetype type) throws Docx4JException {
  return load(file,type,null);
}
