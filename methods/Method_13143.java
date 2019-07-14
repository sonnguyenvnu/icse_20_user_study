protected boolean load(API api,File file,String pathInFile){
  String pathSuffix=pathInFile;
  String path=file.getPath();
  while (!path.endsWith(pathSuffix)) {
    int index=pathSuffix.indexOf(File.separator);
    if (index == -1) {
      pathSuffix="";
    }
 else {
      pathSuffix=pathSuffix.substring(index + 1);
    }
  }
  if (!pathSuffix.isEmpty()) {
    File rootFile=file;
    int index=pathSuffix.indexOf(File.separator);
    while (index != -1) {
      rootFile=rootFile.getParentFile();
      pathSuffix=pathSuffix.substring(index + 1);
      index=pathSuffix.indexOf(File.separator);
    }
    rootFile=rootFile.getParentFile();
    JComponent mainPanel=load(api,rootFile,Paths.get(rootFile.toURI()));
    if (mainPanel instanceof UriOpenable) {
      try {
        pathSuffix=file.getAbsolutePath().substring(rootFile.getAbsolutePath().length()).replace(File.separator,"/");
        URI rootUri=rootFile.toURI();
        URI uri=new URI(rootUri.getScheme(),rootUri.getHost(),rootUri.getPath() + '!' + pathSuffix,null);
        ((UriOpenable)mainPanel).openUri(uri);
        return true;
      }
 catch (      URISyntaxException e) {
        assert ExceptionUtil.printStackTrace(e);
      }
    }
 else {
      return mainPanel != null;
    }
  }
  return false;
}
