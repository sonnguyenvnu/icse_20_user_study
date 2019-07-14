/** 
 * Resets the internal caches used to speed up lookups on this index type. This is needed when the type gets modified in the  {@link org.janusgraph.graphdb.database.management.ManagementSystem}.
 */
@Override public void resetCache(){
  name=null;
  definition=null;
  outRelations=null;
  inRelations=null;
}
