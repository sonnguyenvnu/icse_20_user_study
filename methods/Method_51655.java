/** 
 * Handy method to find a certain pattern into a file. While this method lives in the FileUtils, it was designed with with unit test in mind (to check result redirected into a file)
 * @param file
 * @param pattern
 * @return
 */
public static boolean findPatternInFile(final File file,final String pattern){
  Pattern regexp=Pattern.compile(pattern);
  Matcher matcher=regexp.matcher("");
  FileIterable it=new FileIterable(file);
  for (  String line : it) {
    matcher.reset(line);
    if (matcher.find()) {
      return true;
    }
  }
  return false;
}
