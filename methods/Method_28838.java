/** 
 * Synchronize pipeline by reading all responses. This operation close the pipeline. Whenever possible try to avoid using this version and use Pipeline.sync() as it won't go through all the responses and generate the right response type (usually it is a waste of time).
 * @return A list of all the responses in the order you executed them.
 */
public List<Object> syncAndReturnAll(){
  if (getPipelinedResponseLength() > 0) {
    List<Object> unformatted=client.getMany(getPipelinedResponseLength());
    List<Object> formatted=new ArrayList<Object>();
    for (    Object o : unformatted) {
      try {
        formatted.add(generateResponse(o).get());
      }
 catch (      JedisDataException e) {
        formatted.add(e);
      }
    }
    return formatted;
  }
 else {
    return java.util.Collections.<Object>emptyList();
  }
}
