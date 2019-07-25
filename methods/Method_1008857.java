/** 
 * Load a Docx Document from a File, assigning it an identifier for eventing
 * @since 3.1.0
 */
public static WordprocessingMLPackage load(PackageIdentifier pkgIdentifier,File inFile) throws Docx4JException {
  return (WordprocessingMLPackage)OpcPackage.load(pkgIdentifier,inFile);
}
