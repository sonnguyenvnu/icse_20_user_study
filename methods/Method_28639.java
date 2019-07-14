/** 
 * Alter the configuration of a running Redis server. Not all the configuration parameters are supported. <p> The list of configuration parameters supported by CONFIG SET can be obtained issuing a {@link #configGet(byte[]) CONFIG GET *} command.<p> The configuration set using CONFIG SET is immediately loaded by the Redis server that will start acting as specified starting from the next command. <p> <b>Parameters value format</b> <p> The value of the configuration parameter is the same as the one of the same parameter in the Redis configuration file, with the following exceptions: <p> <ul> <li>The save paramter is a list of space-separated integers. Every pair of integers specify the time and number of changes limit to trigger a save. For instance the command CONFIG SET save "3600 10 60 10000" will configure the server to issue a background saving of the RDB file every 3600 seconds if there are at least 10 changes in the dataset, and every 60 seconds if there are at least 10000 changes. To completely disable automatic snapshots just set the parameter as an empty string. <li>All the integer parameters representing memory are returned and accepted only using bytes as unit. </ul>
 * @param parameter
 * @param value
 * @return Status code reply
 */
@Override public byte[] configSet(final byte[] parameter,final byte[] value){
  client.configSet(parameter,value);
  return client.getBinaryBulkReply();
}
