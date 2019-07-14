/** 
 * Reads the file, which contains the filelist. This is used for the command line arguments --filelist/-filelist for both PMD and CPD. The separator in the filelist is a command and/or newlines.
 * @param filelist the file which contains the list of path names
 * @return a comma-separated list of file paths
 * @throws IOException if the file couldn't be read
 */
public static String readFilelist(File filelist) throws IOException {
  String filePaths=FileUtils.readFileToString(filelist);
  filePaths=StringUtils.trimToEmpty(filePaths);
  filePaths=filePaths.replaceAll("\\r?\\n",",");
  filePaths=filePaths.replaceAll(",+",",");
  return filePaths;
}
