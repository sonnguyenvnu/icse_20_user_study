/** 
 * If  {@code conn} is non-null and is still open, then call{@link org.apache.commons.pool.impl.GenericKeyedObjectPool#returnObject}, catching and logging and Exception that method might generate.  This method does not emit any exceptions.
 * @param keyspace The key of the pooled object being returned
 * @param conn The pooled object being returned, or null to do nothing
 */
public void returnObjectUnsafe(String keyspace,CTConnection conn){
  if (conn != null && conn.isOpen())   try {
    returnObject(keyspace,conn);
  }
 catch (  Exception e) {
    log.warn("Failed to return Cassandra connection to pool",e);
    log.warn("Failure context: keyspace={}, pool={}, connection={}",keyspace,this,conn);
  }
}
