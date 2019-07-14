/** 
 * Returns chunks of action path. Action path is split on  {@code /}. For example, the path  {@code "/hello/world"} would return 2 chunks: {@code hello} and {@code world}.
 */
public String[] getActionPathChunks(){
  return actionPathChunks;
}
