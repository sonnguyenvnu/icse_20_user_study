/** 
 * Compresses a file into zlib archive.
 */
public static File zlib(final File file) throws IOException {
  if (file.isDirectory()) {
    throw new IOException("Can't zlib folder");
  }
  FileInputStream fis=new FileInputStream(file);
  Deflater deflater=new Deflater(Deflater.BEST_COMPRESSION);
  String zlibFileName=file.getAbsolutePath() + ZLIB_EXT;
  DeflaterOutputStream dos=new DeflaterOutputStream(new FileOutputStream(zlibFileName),deflater);
  try {
    StreamUtil.copy(fis,dos);
  }
  finally {
    StreamUtil.close(dos);
    StreamUtil.close(fis);
  }
  return new File(zlibFileName);
}
