/** 
 * Collects a list of DataSources using a comma separated list of input file locations to process. If a file location is a directory, the directory hierarchy will be traversed to look for files. If a file location is a ZIP or Jar the archive will be scanned looking for files. If a file location is a file, it will be used. For each located file, a FilenameFilter is used to decide whether to return a DataSource.
 * @param fileLocations A comma-separated list of file locations.
 * @param filenameFilter The FilenameFilter to apply to files.
 * @return A list of DataSources, one for each file collected.
 */
public static List<DataSource> collectFiles(String fileLocations,FilenameFilter filenameFilter){
  List<DataSource> dataSources=new ArrayList<>();
  for (  String fileLocation : fileLocations.split(",")) {
    collect(dataSources,fileLocation,filenameFilter);
  }
  return dataSources;
}
