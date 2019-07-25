@Override public void connect() throws IOException {
  if (this.jarFile == null) {
    throw FILE_NOT_FOUND_EXCEPTION;
  }
  if (!this.jarEntryName.isEmpty() && this.jarEntry == null) {
    this.jarEntry=this.jarFile.getJarEntry(getEntryName());
    if (this.jarEntry == null) {
      throwFileNotFound(this.jarEntryName,this.jarFile);
    }
  }
  this.connected=true;
}
