/** 
 * Used when module is parsed from an in-memory string.
 * @param path file path
 * @param md5 md5 message digest for source contents
 */
public void setFileAndMD5(String path,String md5) throws Exception {
  file=path;
  name=Util.moduleNameFor(file);
  this.md5=md5;
}
