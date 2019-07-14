/** 
 * Syncronize pipeline by reading all responses. This operation closes the pipeline. Whenever possible try to avoid using this version and use ShardedJedisPipeline.sync() as it won't go through all the responses and generate the right response type (usually it is a waste of time).
 * @return A list of all the responses in the order you executed them.
 */
public List<Object> syncAndReturnAll(){
  List<Object> formatted=new ArrayList<Object>();
  for (  Client client : clients) {
    formatted.add(generateResponse(client.getOne()).get());
  }
  return formatted;
}
