/** 
 * Compresses a given directory in its own location. <p> A ZIP file will be first created with a temporary name. After the compressing the directory will be deleted and the ZIP file will be renamed as the original directory.
 * @param dir input directory as well as the target ZIP file.
 * @param compressionLevel compression level
 * @see #pack(File,File)
 */
public static void unexplode(File dir,int compressionLevel){
  try {
    File zip=FileUtils.getTempFileFor(dir);
    pack(dir,zip,compressionLevel);
    FileUtils.deleteDirectory(dir);
    FileUtils.moveFile(zip,dir);
  }
 catch (  IOException e) {
    throw ZipExceptionUtil.rethrow(e);
  }
}
