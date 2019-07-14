/** 
 * Returns the request URL. 
 */
private String getRequestUrl(String line){
  int end=line.length() - 2;
  while (line.charAt(end) != ' ') {
    end--;
  }
  int start=end - 1;
  while (line.charAt(start) != ' ') {
    start--;
  }
  return line.substring(start + 1,end);
}
