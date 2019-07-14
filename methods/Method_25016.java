@Override public ITempFile createTempFile(String filename_hint) throws Exception {
  DefaultTempFile tempFile=new DefaultTempFile(this.tmpdir);
  this.tempFiles.add(tempFile);
  return tempFile;
}
