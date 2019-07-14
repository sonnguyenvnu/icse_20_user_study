@Override public boolean accept(API api,Path rootPath){
  if (rootPath.toUri().toString().toLowerCase().endsWith(".jar!/")) {
    return true;
  }
 else {
    try {
      return rootPath.getFileSystem().provider().getScheme().equals("file") && Files.exists(rootPath.resolve("META-INF"));
    }
 catch (    InvalidPathException e) {
      assert ExceptionUtil.printStackTrace(e);
      return false;
    }
  }
}
