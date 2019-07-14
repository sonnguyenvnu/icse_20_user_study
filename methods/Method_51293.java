private static List<DataSource> internalGetApplicableFiles(PMDConfiguration configuration,Set<Language> languages){
  LanguageFilenameFilter fileSelector=new LanguageFilenameFilter(languages);
  List<DataSource> files=new ArrayList<>();
  if (null != configuration.getInputPaths()) {
    files.addAll(FileUtil.collectFiles(configuration.getInputPaths(),fileSelector));
  }
  if (null != configuration.getInputUri()) {
    String uriString=configuration.getInputUri();
    try {
      List<DataSource> dataSources=getURIDataSources(uriString);
      files.addAll(dataSources);
    }
 catch (    PMDException ex) {
      LOG.log(Level.SEVERE,"Problem with Input URI",ex);
      throw new RuntimeException("Problem with DBURI: " + uriString,ex);
    }
  }
  if (null != configuration.getInputFilePath()) {
    String inputFilePath=configuration.getInputFilePath();
    File file=new File(inputFilePath);
    try {
      if (!file.exists()) {
        LOG.log(Level.SEVERE,"Problem with Input File Path",inputFilePath);
        throw new RuntimeException("Problem with Input File Path: " + inputFilePath);
      }
 else {
        String filePaths=FileUtil.readFilelist(new File(inputFilePath));
        files.addAll(FileUtil.collectFiles(filePaths,fileSelector));
      }
    }
 catch (    IOException ex) {
      LOG.log(Level.SEVERE,"Problem with Input File",ex);
      throw new RuntimeException("Problem with Input File Path: " + inputFilePath,ex);
    }
  }
  if (null != configuration.getIgnoreFilePath()) {
    String ignoreFilePath=configuration.getIgnoreFilePath();
    File file=new File(ignoreFilePath);
    try {
      if (!file.exists()) {
        LOG.log(Level.SEVERE,"Problem with Ignore File Path",ignoreFilePath);
        throw new RuntimeException("Problem with Ignore File Path: " + ignoreFilePath);
      }
 else {
        String filePaths=FileUtil.readFilelist(new File(ignoreFilePath));
        files.removeAll(FileUtil.collectFiles(filePaths,fileSelector));
      }
    }
 catch (    IOException ex) {
      LOG.log(Level.SEVERE,"Problem with Ignore File",ex);
      throw new RuntimeException("Problem with Ignore File Path: " + ignoreFilePath,ex);
    }
  }
  return files;
}
