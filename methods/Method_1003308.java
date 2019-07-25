/** 
 * Deletes the database files.
 * @param dir the directory
 * @param db the database name (null for all databases)
 * @param quiet don't print progress information
 */
private void process(String dir,String db,boolean quiet){
  ArrayList<String> files=FileLister.getDatabaseFiles(dir,db,true);
  if (files.isEmpty() && !quiet) {
    printNoDatabaseFilesFound(dir,db);
  }
  for (  String fileName : files) {
    process(fileName,quiet);
    if (!quiet) {
      out.println("Processed: " + fileName);
    }
  }
}
