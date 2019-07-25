@Override FileLister.Entry open(String fileName) throws IOException {
  File ret=new File(root.getAbsolutePath() + File.separatorChar + fileName);
  if (ret.exists() && ret.isFile()) {
    return new DirEntry(ret);
  }
  return null;
}
