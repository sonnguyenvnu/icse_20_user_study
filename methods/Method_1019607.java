/** 
 * Initializes this editor with the specified file location.
 * @param loc The file location.  If this is <code>null</code>, a defaultlocation is used and an empty file is displayed.
 * @param defaultEnc The default encoding to use when opening the file,if the file is not Unicode.  If this value is <code>null</code>, a system default value is used.
 * @throws IOException If an IO error occurs reading from <code>loc</code>.If <code>loc</code> is <code>null</code>, this cannot happen.
 */
private void init(FileLocation loc,String defaultEnc) throws IOException {
  if (loc == null) {
    this.loc=FileLocation.create(DEFAULT_FILE_NAME);
    charSet=defaultEnc == null ? getDefaultEncoding() : defaultEnc;
    setLineSeparator(System.getProperty("line.separator"));
  }
 else {
    load(loc,defaultEnc);
  }
  if (this.loc.isLocalAndExists()) {
    File file=new File(this.loc.getFileFullPath());
    lastSaveOrLoadTime=file.lastModified();
    setReadOnly(!file.canWrite());
  }
 else {
    lastSaveOrLoadTime=LAST_MODIFIED_UNKNOWN;
    setReadOnly(false);
  }
  setDirty(false);
}
