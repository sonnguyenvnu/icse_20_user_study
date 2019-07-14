/** 
 * Rewrite the append only file in background when it gets too big. Please for detailed information about the Redis Append Only File check the <a href="http://redis.io/topics/persistence#append-only-file">Append Only File Howto</a>. <p> BGREWRITEAOF rewrites the Append Only File in background when it gets too big. The Redis Append Only File is a Journal, so every operation modifying the dataset is logged in the Append Only File (and replayed at startup). This means that the Append Only File always grows. In order to rebuild its content the BGREWRITEAOF creates a new version of the append only file starting directly form the dataset in memory in order to guarantee the generation of the minimal number of commands needed to rebuild the database. <p>
 * @return Status code reply
 */
@Override public String bgrewriteaof(){
  client.bgrewriteaof();
  return client.getStatusCodeReply();
}
