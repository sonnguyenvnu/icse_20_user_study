/** 
 * Returns a fragment of the source code between the two stated line numbers. The parameters represent <b>inclusive</b> line numbers. <p>The returned fragment will end in a newline.
 */
public String getFragmentByLines(int startLine,int endLine){
  Preconditions.checkArgument(startLine <= endLine);
  return Joiner.on("\n").join(getLines(startLine,endLine)) + "\n";
}
