/** 
 * Decompress gzip archive.
 */
public static File ungzip(final File file) throws IOException {
  String outFileName=FileNameUtil.removeExtension(file.getAbsolutePath());
  File out=new File(outFileName);
  out.createNewFile();
  FileOutputStream fos=new FileOutputStream(out);
  GZIPInputStream gzis=new GZIPInputStream(new FileInputStream(file));
  try {
    StreamUtil.copy(gzis,fos);
  }
  finally {
    StreamUtil.close(fos);
    StreamUtil.close(gzis);
  }
  return out;
}
