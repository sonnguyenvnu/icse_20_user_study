/** 
 * Repacks a provided ZIP file and replaces old file with the new one. <p>
 * @param zip source ZIP file to be repacked and replaced.
 * @param compressionLevel compression level.
 */
public static void repack(File zip,int compressionLevel){
  try {
    File tmpZip=FileUtils.getTempFileFor(zip);
    repack(zip,tmpZip,compressionLevel);
    if (!zip.delete()) {
      throw new IOException("Unable to delete the file: " + zip);
    }
    FileUtils.moveFile(tmpZip,zip);
  }
 catch (  IOException e) {
    throw ZipExceptionUtil.rethrow(e);
  }
}
