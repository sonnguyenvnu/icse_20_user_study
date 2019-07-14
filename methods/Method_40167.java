/** 
 * Copies code from the input source to the output html.
 * @param begin the starting source offset
 * @param end   the end offset, or -1 to go to end of file
 */
private void copySource(int begin,int end){
  try {
    String src=escape((end == -1) ? source.substring(begin) : source.substring(begin,end));
    buffer.append(src);
  }
 catch (  RuntimeException x) {
  }
  sourceOffset=end;
}
