/** 
 * Recursively find a java regex pattern  {@link Pattern} in the file names and publish the result
 * @param file    the current file
 * @param pattern the compiled java regex
 */
private void searchRegExFind(HybridFile file,final Pattern pattern){
  search(file,fileName -> pattern.matcher(fileName).find());
}
