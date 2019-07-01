private List<File> _XXXXX_(String dirSuffix) throws IOException, KeeperException, InterruptedException, BookieException, UnavailableException, CompatibilityException, SecurityException, BKException, ConfigurationException {
  List<File> tempDirs=new ArrayList<File>();
  try {
    _XXXXX_(tempDirs,dirSuffix);
    return tempDirs;
  }
 catch (  IOException ioe) {
    cleanupDirectories(tempDirs);
    throw ioe;
  }
catch (  KeeperException ke) {
    cleanupDirectories(tempDirs);
    throw ke;
  }
catch (  InterruptedException ie) {
    Thread.currentThread().interrupt();
    cleanupDirectories(tempDirs);
    throw ie;
  }
catch (  BookieException be) {
    cleanupDirectories(tempDirs);
    throw be;
  }
catch (  UnavailableException ue) {
    cleanupDirectories(tempDirs);
    throw ue;
  }
catch (  CompatibilityException ce) {
    cleanupDirectories(tempDirs);
    throw ce;
  }
}