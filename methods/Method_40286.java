public void setFile(String file) throws Exception {
  this.file=file;
  this.name=Util.moduleNameFor(file);
  this.md5=Util.getMD5(new File(file));
}
