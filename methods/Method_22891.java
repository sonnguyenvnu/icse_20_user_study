/** 
 * Given the .html file, displays it in the default browser.
 * @param file
 */
public void showReferenceFile(File file){
  try {
    file=file.getCanonicalFile();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  Platform.openURL(file.toURI().toString());
}
