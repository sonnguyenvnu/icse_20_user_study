protected static String shrink(String path,String prefix){
  if (path.equals(prefix)) {
    return "";
  }
  assert path.length() >= prefix.length() : "path: " + path + "; prefix: " + prefix;
  return IFileSystem.SEPARATOR + FileUtil.getRelativePath(path,prefix,IFileSystem.SEPARATOR);
}
