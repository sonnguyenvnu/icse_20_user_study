/** 
 * Matches the supplied filename against this format, returning true if the filename is a valid URI and false otherwise. Protocol-specific matches are handled by  {@link net.pms.encoders.Player#isCompatible(DLNAResource)}.
 * @param filename the filename to match against
 * @return <code>true</code> if the filename matches, <code>false</code> otherwise.
 */
@Override public boolean match(String filename){
  String protocol=FileUtil.getProtocol(filename);
  if (protocol == null) {
    return false;
  }
  url=filename;
  setMatchedExtension(protocol);
  return true;
}
