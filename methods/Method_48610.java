/** 
 * Returns a  {@link RelationIndexStatusWatcher} configured to watch the index specified by{@code relationIndexName} and {@code relationIndexType} through graph {@code g}. <p> This method just instantiates an object. Invoke  {@link RelationIndexStatusWatcher#call()} to wait.
 * @param g                 the graph through which to read index information
 * @param relationIndexName the name of the relation index to watch
 * @param relationTypeName  the type on the relation index to watch
 * @return
 */
public static RelationIndexStatusWatcher awaitRelationIndexStatus(JanusGraph g,String relationIndexName,String relationTypeName){
  return new RelationIndexStatusWatcher(g,relationIndexName,relationTypeName);
}
