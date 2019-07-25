/** 
 * Check if a file exists on the FTP server.
 * @param dir the directory
 * @param name the directory or file name
 * @return true if it exists
 */
public boolean exists(String dir,String name) throws IOException {
  for (  File f : listFiles(dir)) {
    if (f.getName().equals(name)) {
      return true;
    }
  }
  return false;
}
