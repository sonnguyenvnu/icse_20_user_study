private static List<DataSource> collect(List<DataSource> dataSources,String fileLocation,FilenameFilter filenameFilter){
  File file=new File(fileLocation);
  if (!file.exists()) {
    throw new RuntimeException("File " + file.getName() + " doesn't exist");
  }
  if (!file.isDirectory()) {
    if (fileLocation.endsWith(".zip") || fileLocation.endsWith(".jar")) {
      ZipFile zipFile;
      try {
        zipFile=new ZipFile(fileLocation);
        Enumeration<? extends ZipEntry> e=zipFile.entries();
        while (e.hasMoreElements()) {
          ZipEntry zipEntry=e.nextElement();
          if (filenameFilter.accept(null,zipEntry.getName())) {
            dataSources.add(new ZipDataSource(zipFile,zipEntry));
          }
        }
      }
 catch (      IOException ze) {
        throw new RuntimeException("Archive file " + file.getName() + " can't be opened");
      }
    }
 else {
      dataSources.add(new FileDataSource(file));
    }
  }
 else {
    Filter<File> filter=new OrFilter<>(Filters.toFileFilter(filenameFilter),new AndFilter<>(Filters.getDirectoryFilter(),Filters.toNormalizedFileFilter(Filters.buildRegexFilterExcludeOverInclude(null,Collections.singletonList("SCCS")))));
    FileFinder finder=new FileFinder();
    List<File> files=finder.findFilesFrom(file,Filters.toFilenameFilter(filter),true);
    for (    File f : files) {
      dataSources.add(new FileDataSource(f));
    }
  }
  return dataSources;
}
