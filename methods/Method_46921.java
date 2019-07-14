/** 
 * Recursively match a java regex pattern  {@link Pattern} with the file names and publish the result
 * @param file    the current file
 * @param pattern the compiled java regex
 */
private void searchRegExMatch(HybridFile file,final Pattern pattern){
  search(file,fileName -> pattern.matcher(fileName).matches());
}
