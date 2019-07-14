/** 
 * Returns a  {@link GraphIndexStatusWatcher} configured to watch{@code graphIndexName} through graph {@code g}. <p> This method just instantiates an object. Invoke  {@link GraphIndexStatusWatcher#call()} to wait.
 * @param g              the graph through which to read index information
 * @param graphIndexName the name of a graph index to watch
 * @return
 */
public static GraphIndexStatusWatcher awaitGraphIndexStatus(JanusGraph g,String graphIndexName){
  return new GraphIndexStatusWatcher(g,graphIndexName);
}
