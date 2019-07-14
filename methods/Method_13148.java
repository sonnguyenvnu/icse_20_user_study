@Override public boolean load(API api,File file){
  try {
    URI fileUri=file.toURI();
    URI uri=new URI("jar:" + fileUri.getScheme(),fileUri.getHost(),fileUri.getPath() + "!/",null);
    FileSystem fileSystem;
    try {
      fileSystem=FileSystems.getFileSystem(uri);
    }
 catch (    FileSystemNotFoundException e) {
      fileSystem=FileSystems.newFileSystem(uri,Collections.emptyMap());
    }
    if (fileSystem != null) {
      Iterator<Path> rootDirectories=fileSystem.getRootDirectories().iterator();
      if (rootDirectories.hasNext()) {
        return load(api,file,rootDirectories.next()) != null;
      }
    }
  }
 catch (  URISyntaxException|IOException e) {
    assert ExceptionUtil.printStackTrace(e);
  }
  return false;
}
