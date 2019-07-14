/** 
 * Copies code from the input source to the output html.
 * @param beg the starting source offset
 * @param end the end offset, or -1 to go to end of file
 */
private void copySource(int beg,int end){
  try {
    String src=escape((end == -1) ? source.substring(beg) : source.substring(beg,end));
    buffer.append(src);
  }
 catch (  RuntimeException x) {
    System.err.println("Warning: " + x);
  }
  sourceOffset=end;
}
