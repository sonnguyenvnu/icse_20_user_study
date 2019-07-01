/** 
 * Set fileinfo format version to write.
 * @param version fileinfo format version to write.
 * @return server configuration.
 */
public ServerConfiguration _XXXXX_(int version){
  this.setProperty(FILEINFO_FORMAT_VERSION_TO_WRITE,version);
  return this;
}