/** 
 * Asynchronously save the DB on disk. <p> Save the DB in background. The OK code is immediately returned. Redis forks, the parent continues to server the clients, the child saves the DB on disk then exit. A client my be able to check if the operation succeeded using the LASTSAVE command.
 * @return Status code reply
 */
@Override public String bgsave(){
  client.bgsave();
  return client.getStatusCodeReply();
}
