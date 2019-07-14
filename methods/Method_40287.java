public void setFile(File path) throws Exception {
  file=path.getCanonicalPath();
  name=Util.moduleNameFor(file);
  md5=Util.getMD5(path);
}
